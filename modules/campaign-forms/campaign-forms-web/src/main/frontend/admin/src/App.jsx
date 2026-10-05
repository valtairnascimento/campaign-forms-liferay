import {useCallback, useMemo, useRef, useState} from 'react';

import {createCampaignApi} from './api/campaignApi.js';
import {createClient} from './api/client.js';
import {AppContext} from './AppContext.js';
import {Alert} from './components/ui.jsx';
import {createTranslator} from './i18n.js';
import {useRoute} from './router.js';
import {formatDateTime} from './util/dates.js';
import CampaignEditor, {CampaignCreator} from './views/CampaignEditor.jsx';
import CampaignList from './views/CampaignList.jsx';
import NewCampaign from './views/NewCampaign.jsx';
import Results from './views/Results.jsx';

function Toasts({onClose, toasts}) {
	return (
		<div aria-live="polite" className="alert-container container campaign-forms-admin__toasts">
			<div className="alert-notifications alert-notifications-fixed">
				{toasts.map((toast) => (
					<Alert
						displayType={toast.displayType}
						key={toast.id}
						onClose={() => onClose(toast.id)}
					>
						{toast.message}
					</Alert>
				))}
			</div>
		</div>
	);
}

function Route({route}) {
	if (route.name === 'new') {
		return <NewCampaign />;
	}

	if (route.name === 'create') {
		return <CampaignCreator mode={route.mode} />;
	}

	if (route.name === 'edit') {
		return <CampaignEditor campaignId={route.campaignId} step={route.step} />;
	}

	if (route.name === 'results') {
		return <Results campaignId={route.campaignId} />;
	}

	return <CampaignList />;
}

export default function App({api: apiOverride, config}) {
	const route = useRoute();

	const [toasts, setToasts] = useState([]);

	const sequenceRef = useRef(0);

	const closeToast = useCallback(
		(id) => setToasts((previous) => previous.filter((toast) => toast.id !== id)),
		[]
	);

	const notify = useCallback(
		(message, displayType = 'success') => {
			const id = ++sequenceRef.current;

			setToasts((previous) => [...previous, {displayType, id, message}]);

			if (displayType !== 'danger') {
				setTimeout(() => closeToast(id), 5000);
			}
		},
		[closeToast]
	);

	const context = useMemo(() => {
		const api =
			apiOverride ||
			createCampaignApi(
				createClient({apiURL: config.apiURL, locale: config.locale}),
				config.siteId,
				createClient({apiURL: config.deliveryAPIURL, locale: config.locale})
			);

		return {
			api,
			config,
			formatDate: (iso) => formatDateTime(iso, config.locale, config.timeZone),
			notify,
			t: createTranslator(config.messages),
		};
	}, [apiOverride, config, notify]);

	return (
		<AppContext.Provider value={context}>
			<div className="campaign-forms-admin__app container-fluid container-fluid-max-xl">
				<Route route={route} />
			</div>

			<Toasts onClose={closeToast} toasts={toasts} />
		</AppContext.Provider>
	);
}
