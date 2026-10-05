import {spawn} from 'node:child_process';
import {mkdirSync, mkdtempSync} from 'node:fs';
import {tmpdir} from 'node:os';
import {join} from 'node:path';
import {fileURLToPath} from 'node:url';
import {inflateRawSync} from 'node:zlib';
import {chromium} from 'playwright-core';

export const PORTAL_URL = process.env.PORTAL_URL || 'http://127.0.0.1:8080';
export const ADMIN_LOGIN = process.env.PORTAL_LOGIN || 'test@liferay.com';
export const ADMIN_PASSWORD = process.env.PORTAL_PASSWORD || 'test';
export const SITE = process.env.SITE || 'guest';
export const OUTPUT =
	process.env.E2E_OUTPUT ||
	fileURLToPath(new URL('../../../../../build/e2e/', import.meta.url));

export const E2E_EMAIL = 'campanhas.e2e@example.com';
export const E2E_INITIAL_PASSWORD = 'Inicial-E2E-2026';
export const E2E_PASSWORD = 'Campanhas-E2E-2026';
export const E2E_MAIL_SENDER = 'campanhas.e2e@example.com';

export const ADMIN_PORTLET = 'io_github_valtairnascimento_campaignforms_web_portlet_CampaignAdminPortlet';
export const RENDERER_PORTLET = 'io_github_valtairnascimento_campaignforms_web_portlet_CampaignRendererPortlet';

export function log(message) {
	console.log(`[e2e] ${message}`);
}

export function basic(login, password) {
	return `Basic ${Buffer.from(`${login}:${password}`).toString('base64')}`;
}

export async function jsonws(path, params) {
	const response = await fetch(`${PORTAL_URL}/api/jsonws${path}`, {
		body: new URLSearchParams(params),
		headers: {Authorization: basic(ADMIN_LOGIN, ADMIN_PASSWORD)},
		method: 'POST',
	});

	const body = await response.json().catch(() => null);

	if (!response.ok || (body && body.exception)) {
		const error = new Error(`${path}: ${JSON.stringify(body)}`);

		error.body = body;

		throw error;
	}

	return body;
}

export async function headless(method, path, body) {
	const response = await fetch(`${PORTAL_URL}/o/headless-admin-user/v1.0${path}`, {
		body: body ? JSON.stringify(body) : undefined,
		headers: {
			'Accept': 'application/json',
			'Accept-Language': 'pt-BR',
			'Authorization': basic(ADMIN_LOGIN, ADMIN_PASSWORD),
			'Content-Type': 'application/json',
		},
		method,
	});

	const data = await response.json().catch(() => null);

	if (!response.ok) {
		throw new Error(`${method} ${path}: HTTP ${response.status} ${JSON.stringify(data)}`);
	}

	return data;
}

export async function ensureMailSender() {
	const company = await jsonws('/company/get-company-by-web-id', {
		webId: process.env.WEB_ID || 'liferay.com',
	});

	await jsonws('/company/update-preferences', {
		companyId: company.companyId,
		unicodeProperties: JSON.stringify({
			'admin.email.from.address': E2E_MAIL_SENDER,
		}),
	});
}

export async function getSiteId() {
	return (await headless('GET', `/sites/by-friendly-url-path/${SITE}`)).id;
}

export async function ensureE2EUser() {
	const admin = await jsonws('/user/get-current-user', {});

	try {
		const userId = await jsonws('/user/get-user-id-by-email-address', {
			companyId: admin.companyId,
			emailAddress: E2E_EMAIL,
		});

		if (userId) {
			return;
		}
	}
	catch (error) {
	}

	log(`Creating the test user ${E2E_EMAIL}`);

	const user = await headless('POST', '/user-accounts', {
		alternateName: 'campanhas-e2e',
		emailAddress: E2E_EMAIL,
		familyName: 'E2E',
		givenName: 'Campanhas',
		password: E2E_INITIAL_PASSWORD,
	});

	const role = await jsonws('/role/get-role', {
		companyId: admin.companyId,
		name: 'Administrator',
	});

	await headless('POST', `/roles/${role.roleId}/association/user-account/${user.id}`);

	await jsonws('/user/update-agreed-to-terms-of-use', {
		agreedToTermsOfUse: 'true',
		userId: user.id,
	});
	await jsonws('/user/update-reminder-query', {
		answer: 'campaign-forms',
		question: 'what-is-your-father\'s-middle-name',
		userId: user.id,
	});
}

export async function ensureRendererPage(siteId) {
	const page = await fetch(`${PORTAL_URL}/web/${SITE}/campaign`);

	if (page.status === 200) {
		return;
	}

	log('Creating the /campaign page with the Campaign widget');

	const layout = await jsonws('/layout/add-layout', {
		description: '',
		friendlyURL: '/campaign',
		groupId: siteId,
		hidden: 'true',
		name: 'Captação',
		parentLayoutId: '0',
		privateLayout: 'false',
		serviceContext: '{"addGroupPermissions":true,"addGuestPermissions":true}',
		title: 'Captação',
		type: 'portlet',
	});

	await jsonws('/layout/update-layout', {
		groupId: siteId,
		layoutId: layout.layoutId,
		privateLayout: 'false',
		typeSettings: `layout-template-id=1_column\ncolumn-1=${RENDERER_PORTLET}\n`,
	});
}

export async function launchBrowser() {
	if (process.env.CDP_URL) {
		return {
			browser: await chromium.connectOverCDP(process.env.CDP_URL),
			close: () => {},
		};
	}

	const headless = process.env.HEADLESS !== 'false';

	try {
		const browser = await chromium.launch({
			channel: process.env.BROWSER_CHANNEL || 'msedge',
			headless,
			timeout: 15000,
		});

		return {browser, close: () => browser.close()};
	}
	catch (error) {
		log(`launch failed (${error.message.split('\n')[0]}); connecting through CDP`);
	}

	const executable =
		process.env.BROWSER_PATH ||
		'C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe';

	const port = 9300 + Math.floor(Math.random() * 600);

	const child = spawn(
		executable,
		[
			...(headless ? ['--headless'] : []),
			'--disable-gpu',
			'--no-first-run',
			`--remote-debugging-port=${port}`,
			`--user-data-dir=${mkdtempSync(join(tmpdir(), 'campaign-forms-e2e-'))}`,
			'about:blank',
		],
		{stdio: 'ignore'}
	);

	const endpoint = `http://127.0.0.1:${port}`;

	for (let attempt = 0; attempt < 60; attempt++) {
		try {
			const response = await fetch(`${endpoint}/json/version`);

			if (response.ok) {
				const browser = await chromium.connectOverCDP(endpoint);

				return {
					browser,
					close: async () => {
						await browser.close().catch(() => {});

						child.kill();
					},
				};
			}
		}
		catch (error) {
		}

		await new Promise((resolve) => setTimeout(resolve, 500));
	}

	child.kill();

	throw new Error(`The browser did not answer at ${endpoint}`);
}

export function isSignedIn(page) {
	return page
		.waitForFunction(
			() =>
				window.Liferay &&
				window.Liferay.ThemeDisplay &&
				window.Liferay.ThemeDisplay.isSignedIn(),
			null,
			{timeout: 15000}
		)
		.then(
			() => true,
			() => false
		);
}

export async function submitLogin(page, password) {
	await page.goto(`${PORTAL_URL}/c/portal/login`);

	const form = page.locator('form[id$="_loginForm"]');

	await form.locator('input[name$="_login"]').fill(E2E_EMAIL);
	await form.locator('input[name$="_password"]').fill(password);
	await form.locator('button[type="submit"]').click();

	return isSignedIn(page);
}

export async function login(page, adminURL) {
	if (!(await submitLogin(page, E2E_PASSWORD))) {
		if (!(await submitLogin(page, E2E_INITIAL_PASSWORD))) {
			throw new Error(`Login refused for ${E2E_EMAIL}`);
		}
	}

	await page.goto(adminURL);

	const newPassword = page.locator('input[name$="password1"]');

	if (await newPassword.count()) {
		log('First login of the test user: setting the password');

		await newPassword.fill(E2E_PASSWORD);
		await page.locator('input[name$="password2"]').fill(E2E_PASSWORD);
		await Promise.all([
			page.waitForNavigation(),
			page.getByRole('button', {exact: true, name: 'Salvar'}).click(),
		]);

		await page.goto(adminURL);
	}
}

export function readZipEntry(buffer, name) {
	let end = buffer.length - 22;

	while (end >= 0 && buffer.readUInt32LE(end) !== 0x06054b50) {
		end--;
	}

	if (end < 0) {
		throw new Error('Invalid ZIP');
	}

	let offset = buffer.readUInt32LE(end + 16);

	for (let i = 0; i < buffer.readUInt16LE(end + 10); i++) {
		const method = buffer.readUInt16LE(offset + 10);
		const compressedSize = buffer.readUInt32LE(offset + 20);
		const nameLength = buffer.readUInt16LE(offset + 28);
		const extraLength = buffer.readUInt16LE(offset + 30);
		const commentLength = buffer.readUInt16LE(offset + 32);
		const localOffset = buffer.readUInt32LE(offset + 42);

		const entryName = buffer.toString('utf8', offset + 46, offset + 46 + nameLength);

		if (entryName === name) {
			const dataStart =
				localOffset + 30 + buffer.readUInt16LE(localOffset + 26) +
				buffer.readUInt16LE(localOffset + 28);

			const data = buffer.subarray(dataStart, dataStart + compressedSize);

			return (method === 8 ? inflateRawSync(data) : data).toString('utf8');
		}

		offset += 46 + nameLength + extraLength + commentLength;
	}

	throw new Error(`${name} is not in the ZIP`);
}

export const PRIVACY_URL = 'https://www.example.com/privacy';

export async function enableConsent(app) {
	await app.getByLabel(/^Pedir consentimento antes do envio/).check();

	const input = app.getByLabel(/^Link da política de privacidade/);

	if (!(await input.inputValue())) {
		await input.fill(PRIVACY_URL);
	}
}

export async function acceptConsent(guest) {
	const link = guest.getByRole('link', {name: /Política de Privacidade|Privacy Policy/});

	if (!(await link.getAttribute('href'))) {
		throw new Error('Privacy policy link is missing');
	}

	await guest.getByLabel(/Li e concordo|I have read and agree/).check();
}

export const ADMIN_URL = `${PORTAL_URL}/group/${SITE}/~/control_panel/manage?p_p_id=${ADMIN_PORTLET}&p_p_lifecycle=0`;

export async function runScenario(name, scenario) {
	const output = join(OUTPUT, name);

	mkdirSync(output, {recursive: true});

	const shot = (page, shotName) =>
		page.screenshot({fullPage: true, path: join(output, `${shotName}.png`)});

	const siteId = await getSiteId();

	await ensureE2EUser();
	await ensureMailSender();
	await ensureRendererPage(siteId);

	const {browser, close} = await launchBrowser();

	const errors = [];
	const pages = [];

	const newPage = async () => {
		const context = await browser.newContext({
			locale: 'pt-BR',
			viewport: {height: 900, width: 1280},
		});

		const page = await context.newPage();

		pages.push(page);

		page.on('pageerror', (error) => errors.push(`${page.url()}: ${error.message}`));

		return page;
	};

	try {
		const admin = await newPage();

		log('Opening the admin');

		await login(admin, ADMIN_URL);

		const app = admin.locator('.campaign-forms-admin__app');

		await app.getByRole('heading', {name: 'Captações'}).waitFor();

		await scenario({admin, app, newPage, shot, siteId});

		if (errors.length) {
			throw new Error(`JavaScript errors: ${errors.join(' | ')}`);
		}

		log(`${name}: OK`);
	}
	catch (error) {
		for (const [index, page] of pages.entries()) {
			await shot(page, `falha-${index}`).catch(() => {});

			const text = await page
				.locator('body')
				.innerText()
				.catch(() => '');

			log(`Page ${index}: ${page.url()}\n${text.slice(0, 800)}`);
		}

		if (errors.length) {
			log(`JavaScript errors: ${errors.join(' | ')}`);
		}

		throw error;
	}
	finally {
		const ignoreTargetClosed = (error) => {
			if (!error || error.name !== 'TargetClosedError') {
				throw error;
			}
		};

		process.on('unhandledRejection', ignoreTargetClosed);

		for (const page of pages) {
			await page.context().close().catch(() => {});
		}

		await close();

		await new Promise((resolve) => setTimeout(resolve, 500));

		process.off('unhandledRejection', ignoreTargetClosed);
	}
}

export function run(name, scenario) {
	runScenario(name, scenario).catch((error) => {
		console.error(error);

		process.exit(1);
	});
}
