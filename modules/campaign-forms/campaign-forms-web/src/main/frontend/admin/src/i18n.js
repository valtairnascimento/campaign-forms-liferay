export function createTranslator(messages = {}) {
	return function t(key, ...args) {
		const value = Object.prototype.hasOwnProperty.call(messages, key)
			? messages[key]
			: key;

		return value.replace(/\{(\d+)\}/g, (match, index) =>
			args[index] === undefined ? match : String(args[index])
		);
	};
}
