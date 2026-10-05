import en from '../../../../resources/content/Language_en_US.properties?raw';
import pt from '../../../../resources/content/Language_pt_BR.properties?raw';
import base from '../../../../resources/content/Language.properties?raw';

const FILES = {
	'Language.properties': base,
	'Language_en_US.properties': en,
	'Language_pt_BR.properties': pt,
};

export function readProperties(fileName) {
	const properties = {};

	FILES[fileName].split(/\r?\n/).forEach((line) => {
		if (!line.trim() || line.startsWith('#')) {
			return;
		}

		const index = line.indexOf('=');

		properties[line.slice(0, index)] = line
			.slice(index + 1)
			.replace(/\n/g, '\n');
	});

	return properties;
}

export function readAdminMessages(fileName = 'Language.properties') {
	const properties = readProperties(fileName);

	const messages = {};

	Object.keys(properties).forEach((key) => {
		if (key.startsWith('campaign-admin.')) {
			messages[key.slice('campaign-admin.'.length)] = properties[key];
		}
	});

	return messages;
}
