import react from '@vitejs/plugin-react';
import {fileURLToPath} from 'node:url';
import {defineConfig} from 'vite';

export default defineConfig(({mode}) => ({
	build: {
		emptyOutDir: true,
		lib: {
			entry: fileURLToPath(new URL('src/main.jsx', import.meta.url)),
			fileName: () => 'campaign-admin.js',
			formats: ['iife'],
			name: 'CampaignFormsAdmin',
		},
		outDir: fileURLToPath(
			new URL('../../../../build/frontend/admin', import.meta.url)
		),
		rollupOptions: {
			output: {
				assetFileNames: 'campaign-admin[extname]',
			},
		},
		sourcemap: mode === 'development',
		target: 'es2017',
	},

	define:
		mode === 'test'
			? {}
			: {
					'process.env.NODE_ENV': JSON.stringify(
						mode === 'development' ? 'development' : 'production'
					),
				},
	plugins: [react()],
	test: {
		environment: 'jsdom',
		include: ['src/**/*.test.{js,jsx}'],
		setupFiles: ['src/test/setup.js'],
	},
}));
