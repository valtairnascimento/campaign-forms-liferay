import {createContext, useCallback, useContext, useEffect, useRef, useState} from 'react';

export const AppContext = createContext(null);

export function useApp() {
	return useContext(AppContext);
}

export function useAsync(load, deps) {
	const [state, setState] = useState({data: null, error: null, loading: true});

	const requestRef = useRef(0);

	const reload = useCallback(() => {
		const request = ++requestRef.current;

		setState((previous) => ({...previous, error: null, loading: true}));

		return Promise.resolve()
			.then(load)
			.then(
				(data) => {
					if (request === requestRef.current) {
						setState({data, error: null, loading: false});
					}

					return data;
				},
				(error) => {
					if (request === requestRef.current) {
						setState({data: null, error, loading: false});
					}
				}
			);
	}, deps);

	useEffect(() => {
		reload();

		return () => {
			requestRef.current++;
		};
	}, [reload]);

	return {...state, reload, setData: (data) => setState({data, error: null, loading: false})};
}
