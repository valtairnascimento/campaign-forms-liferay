import {screen, waitFor, within} from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import {describe, expect, it, vi} from 'vitest';

import {ApiError} from '../api/client.js';
import {getSteps, parseRoute} from '../router.js';
import BlockDialog from '../views/steps/page/BlockDialog.jsx';
import {insertLink, prefixLines, wrapSelection} from '../views/steps/page/markup.js';
import PageStep from '../views/steps/page/PageStep.jsx';
import {
	cleanConfiguration,
	getAnchors,
	getPreviewURL,
	moveBlock,
	toPagePayload,
	validateBlock,
} from '../views/steps/page/pageModel.js';
import {getIssueStep} from '../views/steps/PublishStep.jsx';
import SeoStep from '../views/steps/SeoStep.jsx';
import TemplateStep from '../views/steps/TemplateStep.jsx';
import {renderWithApp, t} from './renderWithApp.jsx';

const keyT = (key) => key;

const campaign = {
	id: 9,
	mode: 'CUSTOM_PAGE',
	name: 'Encontro Regional',
	permissions: {UPDATE: true},
	slug: 'encontro-regional',
	status: 'DRAFT',
};

const BLOCK_TYPES = [
	{defaultConfiguration: {buttonUrl: '#form', title: 'Encontro Regional'}, description: 'Abertura da página', key: 'HERO', label: 'Abertura', singleton: false},
	{defaultConfiguration: {body: 'Texto padrão'}, description: 'Texto', key: 'TEXT', label: 'Texto', singleton: false},
	{defaultConfiguration: {size: 'content'}, description: 'Imagem', key: 'IMAGE', label: 'Imagem', singleton: false},
	{defaultConfiguration: {items: [{answer: 'Grátis', question: 'Quanto custa?'}], title: 'Perguntas'}, description: 'FAQ', key: 'FAQ', label: 'Perguntas frequentes', singleton: false},
	{defaultConfiguration: {}, description: 'Formulário', key: 'FORM', label: 'Formulário', singleton: true},
];

function page(blocks) {
	return {
		id: 1,
		pageBlocks: blocks,
		seoTitle: 'SEO',
		templateKey: 'event',
		themeKey: 'light',
	};
}

const HERO = {blockKey: 'hero', blockType: 'HERO', configuration: {title: 'Encontro Regional'}, enabled: true, id: 101, position: 0};
const FORM = {blockKey: 'form', blockType: 'FORM', configuration: {}, enabled: true, id: 102, position: 2};
const TEXT = {blockKey: 'text', blockType: 'TEXT', configuration: {body: 'Sobre', title: 'Sobre o evento'}, enabled: true, id: 103, position: 1};

describe('markup editing', () => {
	it('wraps the selection or inserts a placeholder', () => {
		expect(wrapSelection('um texto', 3, 8, '**', 'x')).toEqual({
			selectionEnd: 10,
			selectionStart: 5,
			text: 'um **texto**',
		});
		expect(wrapSelection('', 0, 0, '*', 'itálico').text).toBe('*itálico*');
	});

	it('inserts a link with the address selected', () => {
		const result = insertLink('veja aqui', 5, 9, 'link');

		expect(result.text).toBe('veja [aqui](https://)');
		expect(result.text.slice(result.selectionStart, result.selectionEnd)).toBe('https://');
	});

	it('prefixes every selected line', () => {
		expect(prefixLines('a\nb\nc', 0, 3, '- ').text).toBe('- a\n- b\nc');
		expect(prefixLines('a\nb', 0, 3, '1. ').text).toBe('1. a\n2. b');
		expect(prefixLines('- a', 0, 1, '- ').text).toBe('- a');
	});
});

describe('page model', () => {
	it('validates blocks like the server', () => {
		expect(validateBlock('HERO', {buttonLabel: 'Ir'}, keyT)).toEqual({
			buttonUrl: 'field-required',
			title: 'field-required',
		});
		expect(validateBlock('IMAGE', {decorative: true, imageFileEntryId: 5}, keyT)).toEqual({});
		expect(validateBlock('IMAGE', {imageFileEntryId: 5}, keyT)).toEqual({alt: 'field-required'});
		expect(
			validateBlock('FAQ', {items: [{answer: 'ok', question: ''}, {answer: '', question: 'q'}]}, keyT)
		).toEqual({
			'items[0].question': 'field-required',
			'items[1].answer': 'field-required',
		});
		expect(validateBlock('FAQ', {items: []}, keyT)).toEqual({items: 'faq-items-required'});
	});

	it('builds page payloads and helpers', () => {
		expect(toPagePayload({id: 1, pageBlocks: [], seoTitle: 'A', themeKey: 'light'}, {themeKey: 'accent'})).toEqual({
			seoTitle: 'A',
			themeKey: 'accent',
		});
		expect(getPreviewURL('/web/acme/campaign/-/campaign/', 'abc')).toBe('/web/acme/campaign/-/campaign/abc/preview?p_l_mode=preview');
		expect(getPreviewURL(null, 'abc')).toBeNull();
		expect(getAnchors([HERO, TEXT, FORM], keyT).map((anchor) => anchor.value)).toEqual(['#text', '#form']);
		expect(moveBlock([1, 2, 3], 0, 2)).toEqual([2, 3, 1]);
		expect(cleanConfiguration({a: ' x ', b: '', c: null, d: false, e: 3})).toEqual({a: 'x', e: 3});
	});

	it('has the steps of each mode', () => {
		expect(getSteps('QUICK')).toEqual(['info', 'fields', 'notification', 'confirmation', 'publish']);
		expect(getSteps('CUSTOM_PAGE')).toEqual([
			'info', 'template', 'page', 'fields', 'notification', 'confirmation', 'seo', 'preview', 'publish',
		]);
		expect(parseRoute('#/new/custom')).toEqual({mode: 'CUSTOM_PAGE', name: 'create'});
		expect(parseRoute('#/campaigns/9/page')).toEqual({campaignId: 9, name: 'edit', step: 'page'});
		expect(getIssueStep({code: 'block-needs-review', target: 'image'})).toBe('page');
		expect(getIssueStep({code: 'custom-page-requires-one-form-block'})).toBe('page');
	});
});

describe('PageStep', () => {
	function renderPage(api) {
		return renderWithApp(
			<PageStep campaign={campaign} onBack={vi.fn()} onNext={vi.fn()} onReloadCampaign={vi.fn()} />,
			{api}
		);
	}

	it('lists blocks in order and adds a block with the type defaults', async () => {
		const user = userEvent.setup();

		const api = {
			addBlock: vi.fn(() => Promise.resolve({})),
			getBlockTypes: vi.fn(() => Promise.resolve(BLOCK_TYPES)),
			getPage: vi.fn(() => Promise.resolve(page([FORM, HERO, TEXT]))),
		};

		renderPage(api);

		const list = await screen.findByRole('list', {busy: false});

		const titles = within(list)
			.getAllByRole('listitem')
			.map((item) => item.querySelector('.list-group-subtext').textContent);

		expect(titles).toEqual(['Encontro Regional · #hero', 'Sobre o evento · #text', 'Formulário · #form']);

		expect(screen.getByTitle(t('preview-of-x').replace('{0}', 'Encontro Regional')).getAttribute('src')).toBe(
			'http://localhost:8080/web/acme/campaign/-/campaign/encontro-regional/preview?p_l_mode=preview&v=0'
		);

		await user.click(screen.getByRole('button', {name: t('add-block')}));

		const palette = screen.getByRole('list', {name: t('block-types')});

		expect(within(palette).getByRole('button', {name: /Formulário/}).disabled).toBe(true);

		await user.click(within(palette).getByRole('button', {name: /Perguntas frequentes/}));

		const dialog = screen.getByRole('dialog');

		expect(within(dialog).getByLabelText(/^Pergunta \*|^Pergunta/).value).toBe('Quanto custa?');

		await user.click(within(dialog).getByRole('button', {name: t('add-block')}));

		await waitFor(() => expect(api.addBlock).toHaveBeenCalled());

		expect(api.addBlock.mock.calls[0]).toEqual([
			9,
			{
				blockType: 'FAQ',
				configuration: {items: [{answer: 'Grátis', question: 'Quanto custa?'}], title: 'Perguntas'},
				enabled: true,
			},
		]);
	});

	it('reorders with the arrow buttons and hides blocks', async () => {
		const user = userEvent.setup();

		const api = {
			getBlockTypes: vi.fn(() => Promise.resolve(BLOCK_TYPES)),
			getPage: vi.fn(() => Promise.resolve(page([HERO, TEXT, FORM]))),
			reorderBlocks: vi.fn(() => Promise.resolve([])),
			updateBlock: vi.fn(() => Promise.resolve({})),
		};

		renderPage(api);

		await user.click(await screen.findByRole('button', {name: t('move-down-x').replace('{0}', 'Encontro Regional')}));

		await waitFor(() => expect(api.reorderBlocks).toHaveBeenCalledWith(9, [103, 101, 102]));

		await user.click(screen.getByRole('button', {name: t('actions-for-x').replace('{0}', 'Sobre o evento')}));
		await user.click(screen.getByRole('menuitem', {name: t('hide-block')}));

		await waitFor(() =>
			expect(api.updateBlock).toHaveBeenCalledWith(103, {configuration: TEXT.configuration, enabled: false})
		);
	});

	it('shows server validation errors on the block field', async () => {
		const user = userEvent.setup();

		const api = {
			getBlockTypes: vi.fn(() => Promise.resolve(BLOCK_TYPES)),
			getPage: vi.fn(() => Promise.resolve(page([TEXT]))),
			updateBlock: vi.fn(() =>
				Promise.reject(
					new ApiError({
						code: 'VALIDATION_ERROR',
						fieldErrors: {body: 'Há um link não permitido no texto.'},
						status: 400,
					})
				)
			),
		};

		renderPage(api);

		await user.click(await screen.findByRole('button', {name: t('actions-for-x').replace('{0}', 'Sobre o evento')}));
		await user.click(screen.getByRole('menuitem', {name: t('edit')}));

		const dialog = screen.getByRole('dialog');

		await user.click(within(dialog).getByRole('button', {name: t('save')}));

		expect(await within(dialog).findByText('Há um link não permitido no texto.')).toBeTruthy();
		expect(within(dialog).getByLabelText(/^Texto/).getAttribute('aria-invalid')).toBe('true');
	});
});

describe('BlockDialog', () => {
	it('picks an image from the site library', async () => {
		const user = userEvent.setup();

		const onSave = vi.fn(() => Promise.resolve());

		const api = {
			getImage: vi.fn(() =>
				Promise.resolve({contentUrl: '/documents/1/banner.png', id: 77, title: 'banner.png'})
			),
			getImages: vi.fn(() =>
				Promise.resolve({
					items: [{contentUrl: '/documents/1/banner.png', id: 77, title: 'banner.png'}],
					lastPage: 1,
				})
			),
		};

		renderWithApp(
			<BlockDialog
				anchors={[]}
				block={{blockType: 'IMAGE', configuration: {size: 'content'}, enabled: true}}
				onClose={vi.fn()}
				onSave={onSave}
			/>,
			{api}
		);

		await user.click(screen.getByRole('button', {name: t('add-block')}));

		expect(screen.getAllByText(t('field-required')).length).toBe(2);

		await user.click(screen.getByRole('button', {name: t('choose-image')}));
		await user.click(await screen.findByRole('button', {name: 'banner.png'}));

		await user.type(screen.getByLabelText(/^Texto alternativo/), 'Auditório cheio');
		await user.click(screen.getByRole('button', {name: t('add-block')}));

		await waitFor(() => expect(onSave).toHaveBeenCalled());

		expect(onSave.mock.calls[0][0]).toEqual({
			alt: 'Auditório cheio',
			imageFileEntryId: 77,
			size: 'content',
		});
	});
});

describe('TemplateStep', () => {
	const templates = [
		{blockTypes: ['HERO', 'TEXT', 'FORM', 'FAQ'], description: 'Evento', key: 'event', label: 'Evento regional'},
		{blockTypes: ['HERO', 'FORM'], description: 'Em branco', key: 'blank', label: 'Em branco'},
	];

	it('applies a template right away on an empty page', async () => {
		const user = userEvent.setup();

		const onGoToStep = vi.fn();

		const api = {
			applyPageTemplate: vi.fn(() => Promise.resolve({})),
			getPage: vi.fn(() => Promise.resolve(page([]))),
			getPageTemplates: vi.fn(() => Promise.resolve(templates)),
		};

		renderWithApp(
			<TemplateStep campaign={campaign} onBack={vi.fn()} onGoToStep={onGoToStep} onNext={vi.fn()} onReloadCampaign={vi.fn()} />,
			{api}
		);

		await user.click(await screen.findByRole('button', {name: t('use-template-x').replace('{0}', 'Evento regional')}));

		await waitFor(() => expect(onGoToStep).toHaveBeenCalledWith('page'));

		expect(api.applyPageTemplate).toHaveBeenCalledWith(9, 'event');
	});

	it('asks before replacing existing blocks', async () => {
		const user = userEvent.setup();

		const api = {
			applyPageTemplate: vi.fn(() => Promise.resolve({})),
			getPage: vi.fn(() => Promise.resolve(page([HERO]))),
			getPageTemplates: vi.fn(() => Promise.resolve(templates)),
		};

		renderWithApp(
			<TemplateStep campaign={campaign} onBack={vi.fn()} onGoToStep={vi.fn()} onNext={vi.fn()} onReloadCampaign={vi.fn()} />,
			{api}
		);

		await user.click(await screen.findByRole('button', {name: t('use-template-x').replace('{0}', 'Em branco')}));

		expect(api.applyPageTemplate).not.toHaveBeenCalled();

		await user.click(within(screen.getByRole('dialog')).getByRole('button', {name: t('use-template')}));

		await waitFor(() => expect(api.applyPageTemplate).toHaveBeenCalledWith(9, 'blank'));
	});
});

describe('SeoStep', () => {
	it('saves SEO keeping the other page settings', async () => {
		const user = userEvent.setup();

		const onNext = vi.fn();

		const api = {
			getPage: vi.fn(() => Promise.resolve(page([HERO]))),
			updatePage: vi.fn(() => Promise.resolve({})),
		};

		renderWithApp(
			<SeoStep campaign={campaign} onBack={vi.fn()} onNext={onNext} onReloadCampaign={vi.fn()} />,
			{api}
		);

		const title = await screen.findByLabelText(/^Título para buscadores/);

		await user.clear(title);
		await user.type(title, 'Encontro Regional de RH');
		await user.click(screen.getByLabelText(t('no-index')));
		await user.click(screen.getByRole('button', {name: t('save-and-continue')}));

		await waitFor(() => expect(onNext).toHaveBeenCalled());

		expect(api.updatePage).toHaveBeenCalledWith(9, {
			noIndex: true,
			ogImageFileEntryId: 0,
			seoTitle: 'Encontro Regional de RH',
			templateKey: 'event',
			themeKey: 'light',
		});
	});
});
