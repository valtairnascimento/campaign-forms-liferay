import {render} from '@testing-library/react';
import {vi} from 'vitest';

import {AppContext} from '../AppContext.js';
import {createTranslator} from '../i18n.js';
import {readAdminMessages} from './languages.js';

const messages = readAdminMessages('Language_pt_BR.properties');

export const baseConfig = {
	apiURL: '/o/campaign-forms-admin/v1.0',
	languageId: 'pt_BR',
	locale: 'pt-BR',
	messages,
	permissions: {ADD_CAMPAIGN: true},
	publicURL: 'http://localhost:8080/web/acme/campaign/-/campaign/',
	siteId: 20121,
	spritemap: '/icons.svg',
	timeZone: 'America/Sao_Paulo',
};

export const t = createTranslator(messages);

export function renderWithApp(ui, {api = {}, config = {}} = {}) {
	const context = {
		api,
		config: {...baseConfig, ...config},
		formatDate: (iso) => (iso ? `formatted:${iso}` : ''),
		notify: vi.fn(),
		t,
	};

	return {context, ...render(<AppContext.Provider value={context}>{ui}</AppContext.Provider>)};
}
