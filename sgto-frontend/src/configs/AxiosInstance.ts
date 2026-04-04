import axios from 'axios';
import qs from 'qs';

export const AxiosInstanceConfig = {
    baseURL: import.meta.env.VITE_API_URL,
    timeout: 10000,
    paramsSerializer: (params: Record<string, unknown>) => qs.stringify(params, {skipNulls: true})
}

export const AxiosPublicInstance = axios.create(AxiosInstanceConfig);
export const AxiosAuthInstance = axios.create(AxiosInstanceConfig);

AxiosAuthInstance.interceptors.request.use((config) => {
    const token = localStorage.getItem('token');
    if (token) config.headers.Authorization = `Bearer ${token}`
    return config;
});

AxiosAuthInstance.interceptors.response.use(
    (response) => response,
    (error) => {
        if (error.response?.status === 401) {
            localStorage.removeItem('token');
            window.location.href = '/login';
        }
        return Promise.reject(error);
    }
);