import {useEffect, useState} from 'react';

const STEPS_BY_MODE = {
	CUSTOM_PAGE: [
		'info',
		'template',
		'page',
		'fields',
		'notification',
		'confirmation',
		'seo',
		'preview',
		'publish',
	],
	QUICK: ['info', 'fields', 'notification', 'confirmation', 'publish'],
};

export const ALL_STEPS = STEPS_BY_MODE.CUSTOM_PAGE;

export function getSteps(mode) {
	return STEPS_BY_MODE[mode] || STEPS_BY_MODE.QUICK;
}

export function parseRoute(hash) {
	const path = String(hash || '')
		.replace(/^#\/?/, '')
		.split('?')[0];

	const parts = path.split('/').filter(Boolean);

	if (parts[0] === 'new') {
		if (parts[1] === 'quick') {
			return {mode: 'QUICK', name: 'create'};
		}

		if (parts[1] === 'custom') {
			return {mode: 'CUSTOM_PAGE', name: 'create'};
		}

		return {name: 'new'};
	}

	if (parts[0] === 'campaigns' && /^\d+$/.test(parts[1] || '')) {
		const campaignId = Number(parts[1]);

		if (parts[2] === 'results') {
			return {campaignId, name: 'results'};
		}

		return {
			campaignId,
			name: 'edit',
			step: ALL_STEPS.includes(parts[2]) ? parts[2] : ALL_STEPS[0],
		};
	}

	return {name: 'list'};
}

export function navigate(path) {
	window.location.hash = `#${path}`;
}

export function useRoute() {
	const [route, setRoute] = useState(() => parseRoute(window.location.hash));

	useEffect(() => {
		const handleHashChange = () => {
			setRoute(parseRoute(window.location.hash));
		};

		window.addEventListener('hashchange', handleHashChange);

		return () => window.removeEventListener('hashchange', handleHashChange);
	}, []);

	return route;
}
