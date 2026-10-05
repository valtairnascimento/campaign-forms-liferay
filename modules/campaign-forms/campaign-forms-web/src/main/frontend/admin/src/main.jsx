import {createRoot} from 'react-dom/client';

import App from './App.jsx';

import './admin.css';

const CONFIG_ATTRIBUTE = 'data-campaign-forms-admin-config';

const roots = new Map();

export function mount(element) {
	if (roots.has(element)) {
		return;
	}

	let config;

	try {
		config = JSON.parse(element.getAttribute(CONFIG_ATTRIBUTE));
	}
	catch (error) {
		console.error('Campaign Forms Admin: invalid configuration', error);

		return;
	}

	const root = createRoot(element);

	roots.set(element, root);

	root.render(<App config={config} />);

	const Liferay = window.Liferay;

	if (Liferay && typeof Liferay.once === 'function') {
		Liferay.once('beforeNavigate', () => unmount(element));
	}
}

export function unmount(element) {
	const root = roots.get(element);

	if (root) {
		root.unmount();

		roots.delete(element);
	}
}

export function mountAll() {
	document.querySelectorAll(`[${CONFIG_ATTRIBUTE}]`).forEach(mount);
}

if (document.readyState === 'loading') {
	document.addEventListener('DOMContentLoaded', mountAll);
}
else {
	mountAll();
}
