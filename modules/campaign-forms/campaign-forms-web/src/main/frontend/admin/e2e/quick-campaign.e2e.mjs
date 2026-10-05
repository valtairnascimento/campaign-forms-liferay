import {readFileSync} from 'node:fs';

import {
	E2E_EMAIL,
	PORTAL_URL,
	acceptConsent,
	enableConsent,
	log,
	readZipEntry,
	run,
} from './support.mjs';

run('quick-campaign', async ({admin, app, newPage, shot}) => {
	await shot(admin, '01-lista');

	const stamp = Date.now().toString(36);
	const name = `Encontro de RH E2E ${stamp}`;

	await app.getByRole('button', {name: 'Nova captação'}).first().click();
	await app.getByRole('button', {name: /Formulário rápido/}).click();

	await app.getByLabel(/^Nome/).fill(name);

	const slug = await app.getByLabel(/^Endereço \(URL\)/).inputValue();

	if (slug !== `encontro-de-rh-e2e-${stamp}`) {
		throw new Error(`Unexpected suggested slug: ${slug}`);
	}

	await shot(admin, '02-informacoes');

	await app.getByRole('button', {name: 'Criar e continuar'}).click();

	await app.getByRole('heading', {name: 'Campos'}).waitFor();

	for (const preset of ['Nome', 'E-mail', 'Telefone']) {
		await app.getByRole('button', {exact: true, name: preset}).click();
		await admin
			.locator('.campaign-forms-admin__fields .list-group-title', {hasText: preset})
			.first()
			.waitFor();
	}

	await shot(admin, '03-campos');

	const campaignId = /#\/campaigns\/(\d+)\//.exec(admin.url())[1];

	log(`Campaign ${campaignId} created (${slug})`);

	await enableConsent(app);
	await app.getByRole('button', {name: 'Salvar e continuar'}).click();

	await app.getByRole('heading', {name: 'Notificação'}).waitFor();
	await app.getByRole('button', {name: 'Aviso para a equipe'}).click();

	const dialog = admin.getByRole('dialog');

	await dialog.getByLabel(/^Destinatários/).fill('rh@example.com');
	await dialog.getByLabel(/^Assunto/).fill('Nova inscrição: ${campaign.name}');

	await shot(admin, '04-notificacao');

	await dialog.getByRole('button', {name: 'Salvar'}).click();
	await dialog.waitFor({state: 'detached'});

	await app.getByText('rh@example.com').waitFor();

	await app.getByRole('button', {name: 'Ações de Aviso para a equipe'}).click();
	await app.getByRole('menuitem', {name: 'Enviar e-mail de teste'}).click();
	await admin.getByText(`E-mail de teste enviado para ${E2E_EMAIL}.`).waitFor();

	log('Test email handed to the MailService');

	await app.getByRole('button', {name: 'Continuar'}).click();

	await app.getByRole('heading', {name: 'Confirmação'}).waitFor();
	await app.getByLabel(/^Título/).fill('Inscrição confirmada!');
	await app.getByLabel(/^Mensagem/).fill('Até o encontro.');
	await app.getByRole('button', {name: 'Salvar e continuar'}).click();

	await app.getByRole('heading', {name: 'Publicar'}).waitFor();
	await app.getByText('Tudo certo para publicar.').waitFor();
	await app.getByRole('button', {exact: true, name: 'Publicar'}).click();
	await admin.getByText('Captação publicada.').waitFor();

	const publicURL = await app.getByLabel('Endereço público').inputValue();

	await shot(admin, '05-publicada');

	log(`Published at ${publicURL}`);

	const guest = await newPage();

	await guest.goto(publicURL);

	await guest.getByRole('heading', {level: 1, name}).waitFor();

	await guest.getByLabel('Nome').fill('Maria E2E');
	await guest.getByLabel('E-mail').fill('maria.e2e@example.com');
	await guest.getByLabel('Telefone').fill('(41) 99999-0000');
	await acceptConsent(guest);

	await guest.waitForTimeout(2500);

	await shot(guest, '06-formulario-publico');

	await guest.getByRole('button', {name: /^(Enviar|Submit)$/}).click();
	await guest.getByText('Inscrição confirmada!').waitFor();

	await shot(guest, '07-enviado');

	log('Submission sent by the guest');

	await admin.goto(admin.url().replace(/#.*$/, `#/campaigns/${campaignId}/results`));
	await app.getByRole('heading', {name: 'Resultados'}).waitFor();
	await app.getByRole('cell', {name: 'Maria E2E'}).waitFor();

	await shot(admin, '08-resultados');

	log('Submission visible in Results');

	await app.getByRole('row', {name: /Maria E2E/}).getByRole('button').first().click();

	let dialog2 = admin.getByRole('dialog');

	await dialog2.getByRole('heading', {name: 'Envios'}).waitFor();
	await dialog2.getByText('Enviado').waitFor();

	await shot(admin, '09-envios');

	await dialog2.getByRole('button', {exact: true, name: 'Fechar'}).last().click();
	await dialog2.waitFor({state: 'detached'});

	log('Submission notification sent');

	await app.getByRole('button', {name: 'Exportação completa (CSV)'}).click();

	dialog2 = admin.getByRole('dialog');

	const [download] = await Promise.all([
		admin.waitForEvent('download'),
		dialog2.getByRole('button', {name: 'Baixar CSV'}).click(),
	]);

	const csv = readFileSync(await download.path(), 'utf8');

	if (!download.suggestedFilename().startsWith(`submissions-${campaignId}-`) ||
		csv.charCodeAt(0) !== 0xfeff || !csv.includes(';Maria E2E;')) {
		throw new Error(`Unexpected CSV (${download.suggestedFilename()}): ${csv.slice(0, 300)}`);
	}

	log(`CSV downloaded: ${download.suggestedFilename()}`);

	const [excel] = await Promise.all([
		admin.waitForEvent('download'),
		app.getByRole('button', {name: 'Baixar planilha (Excel)'}).click(),
	]);

	const sheet = readZipEntry(readFileSync(await excel.path()), 'xl/worksheets/sheet1.xml');

	if (!excel.suggestedFilename().endsWith('.xlsx') ||
		!sheet.includes('>Nome</t>') || !sheet.includes('>Maria E2E</t>') ||
		sheet.includes('submissionId')) {
		throw new Error(`Unexpected spreadsheet (${excel.suggestedFilename()}): ${sheet.slice(0, 400)}`);
	}

	log(`Spreadsheet downloaded: ${excel.suggestedFilename()}`);

	await app.getByRole('button', {name: 'Power BI'}).click();

	dialog2 = admin.getByRole('dialog');

	await dialog2.getByRole('button', {name: 'Nova URL'}).click();
	await dialog2.getByLabel(/^Nome/).fill('Painel E2E');
	await dialog2.getByRole('button', {name: 'Criar URL'}).click();

	const feedURL = await dialog2.getByLabel('URL de "Painel E2E"').inputValue();

	await shot(admin, '10-power-bi');

	let feed = await fetch(feedURL.replace(/^https?:\/\/[^/]+/, PORTAL_URL));

	const feedCSV = await feed.text();

	if (feed.status !== 200 || !feedCSV.includes('Maria E2E')) {
		throw new Error(`Feed: HTTP ${feed.status} ${feedCSV.slice(0, 300)}`);
	}

	log('Power BI URL returns the submissions without login');

	await dialog2.getByRole('button', {name: 'Concluído'}).click();
	await dialog2.getByText('Painel E2E').waitFor();
	await dialog2.getByRole('button', {name: 'Revogar'}).click();

	const confirm = admin.getByRole('dialog').filter({hasText: 'Revogar URL'});

	await confirm.getByRole('button', {name: 'Revogar'}).click();
	await admin.getByText('URL revogada. Ela não funciona mais.').waitFor();

	feed = await fetch(feedURL.replace(/^https?:\/\/[^/]+/, PORTAL_URL));

	if (feed.status !== 401) {
		throw new Error(`Revoked feed answered HTTP ${feed.status}`);
	}

	log('Revoked URL: 401');
});
