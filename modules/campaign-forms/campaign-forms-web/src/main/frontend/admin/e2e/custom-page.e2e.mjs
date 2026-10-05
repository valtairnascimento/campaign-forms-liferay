import {writeFileSync} from 'node:fs';
import {tmpdir} from 'node:os';
import {join} from 'node:path';
import {deflateSync} from 'node:zlib';

import {log, run} from './support.mjs';

function createPNG() {
	const crcTable = [];

	for (let n = 0; n < 256; n++) {
		let c = n;

		for (let k = 0; k < 8; k++) {
			c = c & 1 ? 0xedb88320 ^ (c >>> 1) : c >>> 1;
		}

		crcTable[n] = c >>> 0;
	}

	const crc = (buffer) => {
		let value = 0xffffffff;

		for (const byte of buffer) {
			value = crcTable[(value ^ byte) & 255] ^ (value >>> 8);
		}

		return (value ^ 0xffffffff) >>> 0;
	};

	const chunk = (type, data) => {
		const length = Buffer.alloc(4);

		length.writeUInt32BE(data.length);

		const typeAndData = Buffer.concat([Buffer.from(type), data]);

		const checksum = Buffer.alloc(4);

		checksum.writeUInt32BE(crc(typeAndData));

		return Buffer.concat([length, typeAndData, checksum]);
	};

	const width = 120;
	const height = 60;

	const raw = Buffer.alloc((width * 3 + 1) * height);

	for (let y = 0; y < height; y++) {
		for (let x = 0; x < width; x++) {
			const offset = y * (width * 3 + 1) + 1 + x * 3;

			raw[offset] = 11;
			raw[offset + 1] = 95;
			raw[offset + 2] = 255;
		}
	}

	const header = Buffer.alloc(13);

	header.writeUInt32BE(width, 0);
	header.writeUInt32BE(height, 4);
	header[8] = 8;
	header[9] = 2;

	const file = join(tmpdir(), `auditorio-e2e-${Date.now()}.png`);

	writeFileSync(
		file,
		Buffer.concat([
			Buffer.from([137, 80, 78, 71, 13, 10, 26, 10]),
			chunk('IHDR', header),
			chunk('IDAT', deflateSync(raw)),
			chunk('IEND', Buffer.alloc(0)),
		])
	);

	return file;
}

run('custom-page', async ({admin, app, newPage, shot}) => {
	const stamp = Date.now().toString(36);
	const name = `Encontro Regional E2E ${stamp}`;

	await app.getByRole('button', {name: 'Nova captação'}).first().click();
	await app.getByRole('button', {name: /Página personalizada/}).click();

	await app.getByLabel(/^Nome/).fill(name);
	await app.getByRole('button', {name: 'Criar e continuar'}).click();

	await app.getByRole('heading', {name: 'Modelo'}).waitFor();

	await shot(admin, '01-modelos');

	await app.getByRole('button', {name: 'Usar o modelo Evento regional'}).click();

	await app.getByRole('heading', {exact: true, name: 'Página'}).waitFor();

	const blocks = admin.locator('.campaign-forms-admin__blocks .list-group-subtext');

	await blocks.nth(3).waitFor();

	const campaignId = /#\/campaigns\/(\d+)\//.exec(admin.url())[1];

	log(`Campaign ${campaignId} from the template: ${(await blocks.allInnerTexts()).join(' | ')}`);

	await app.getByRole('button', {name: 'Ações de Sobre o evento'}).click();
	await app.getByRole('menuitem', {name: 'Editar'}).click();

	let dialog = admin.getByRole('dialog');

	await dialog.getByLabel(/^Texto/).fill(
		'O encontro reúne **empresas da região**.\n\n- palestras\n- networking'
	);
	await dialog.getByRole('button', {name: 'Salvar'}).click();
	await dialog.waitFor({state: 'detached'});

	await app.getByRole('button', {name: 'Mover "Perguntas frequentes" para cima'}).click();

	await admin.waitForFunction(() =>
		Array.from(document.querySelectorAll('.campaign-forms-admin__blocks .list-group-subtext'))
			.map((element) => element.textContent)
			.join('|')
			.includes('#faq|')
	);

	await app.getByRole('button', {exact: true, name: 'Adicionar bloco'}).click();
	await app
		.getByRole('list', {name: 'Tipos de bloco'})
		.getByRole('button', {name: /^Imagem/})
		.click();

	dialog = admin.getByRole('dialog');

	await dialog.getByRole('button', {name: 'Escolher imagem'}).click();

	const picker = admin.getByRole('dialog').last();

	await picker.locator('input[type="file"]').setInputFiles(createPNG());

	await admin.waitForFunction(
		() => document.querySelectorAll('[role="dialog"]').length === 1
	);
	await admin.locator('.campaign-forms-admin__image-preview img').waitFor();

	dialog = admin.getByRole('dialog');

	await dialog.getByLabel(/^Texto alternativo/).fill('Auditório do encontro');
	await dialog.getByLabel(/^Legenda/).fill('Edição anterior');

	await shot(admin, '02-bloco-imagem');

	await dialog.getByRole('button', {name: 'Adicionar bloco'}).click();
	await dialog.waitFor({state: 'detached'});

	await blocks.nth(4).waitFor();

	const livePreview = admin.frameLocator('iframe[title^="Pré-visualização de"]');

	await livePreview.getByRole('heading', {level: 1, name}).waitFor();
	await livePreview.getByText('empresas da região').waitFor();

	await shot(admin, '03-pagina');

	await app.getByRole('button', {name: 'Continuar'}).click();

	await app.getByRole('heading', {name: 'Campos'}).waitFor();

	for (const preset of ['Nome', 'E-mail']) {
		await app.getByRole('button', {exact: true, name: preset}).click();
		await admin
			.locator('.campaign-forms-admin__fields .list-group-title', {hasText: preset})
			.first()
			.waitFor();
	}

	await app.getByRole('button', {name: 'Salvar e continuar'}).click();

	await app.getByRole('heading', {name: 'Notificação'}).waitFor();
	await app.getByRole('button', {name: 'Continuar'}).click();

	await app.getByRole('heading', {name: 'Confirmação'}).waitFor();
	await app.getByLabel(/^Título/).fill('Vaga garantida!');
	await app.getByRole('button', {name: 'Salvar e continuar'}).click();

	await app.getByRole('heading', {exact: true, name: 'SEO'}).waitFor();
	await app.getByLabel(/^Título para buscadores/).fill(`Encontro Regional ${stamp} | Acme`);
	await app.getByLabel(/^Descrição para buscadores/).fill('Inscrições abertas para o encontro regional.');

	await shot(admin, '04-seo');

	await app.getByRole('button', {name: 'Salvar e continuar'}).click();

	await app.getByRole('heading', {name: 'Pré-visualização'}).waitFor();
	await app.getByRole('button', {name: 'Celular'}).click();

	const preview = admin.frameLocator('iframe[title^="Pré-visualização de"]');

	await preview.getByText('Pré-visualização do rascunho').waitFor();
	await preview.getByRole('heading', {level: 1, name}).waitFor();

	await shot(admin, '05-previa-celular');

	await app.getByRole('button', {name: 'Continuar'}).click();

	await app.getByRole('heading', {name: 'Publicar'}).waitFor();
	await app.getByText('Tudo certo para publicar.').waitFor();
	await app.getByRole('button', {exact: true, name: 'Publicar'}).click();
	await admin.getByText('Captação publicada.').waitFor();

	const publicURL = await app.getByLabel('Endereço público').inputValue();

	log(`Published at ${publicURL}`);

	const guest = await newPage();

	await guest.goto(publicURL);

	await guest.getByRole('heading', {level: 1, name}).waitFor();

	const order = await guest.evaluate(() =>
		Array.from(document.querySelectorAll('.campaign-forms__block')).map((element) => element.id)
	);

	if (order.join(',') !== 'hero,text,faq,form,image') {
		throw new Error(`Unexpected block order: ${order.join(',')}`);
	}

	await guest.locator('strong', {hasText: 'empresas da região'}).waitFor();
	await guest.getByRole('img', {name: 'Auditório do encontro'}).waitFor();

	const image = guest.getByRole('img', {name: 'Auditório do encontro'});

	await image.scrollIntoViewIfNeeded();

	await image.evaluate(
		(element) =>
			new Promise((resolve, reject) => {
				const check = () =>
					element.naturalWidth > 0 ?
						resolve() :
						reject(new Error('The image did not load for the guest'));

				if (element.complete) {
					check();
				}
				else {
					element.addEventListener('load', check);
					element.addEventListener('error', check);
				}
			})
	);

	const title = await guest.title();

	if (!title.startsWith(`Encontro Regional ${stamp} | Acme`)) {
		throw new Error(`Unexpected title: ${title}`);
	}

	await guest.getByText('Quanto custa participar?').click();
	await guest.getByText('A participação é gratuita.').waitFor();

	await guest.getByLabel('Nome').fill('João E2E');
	await guest.getByLabel('E-mail').fill('joao.e2e@example.com');

	await guest.waitForTimeout(2500);

	await shot(guest, '06-pagina-publica');

	await guest.getByRole('button', {name: /^(Enviar|Submit)$/}).click();
	await guest.getByText('Vaga garantida!').waitFor();

	await shot(guest, '07-enviado');

	log('Public page in the right order and submission sent');
});
