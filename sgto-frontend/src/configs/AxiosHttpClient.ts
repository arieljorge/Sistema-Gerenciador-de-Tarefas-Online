import type {AxiosInstance, AxiosRequestConfig, AxiosResponse} from "axios";

export class AxiosHttpClient {
    axiosInstance: AxiosInstance;

    constructor(axiosInstance: AxiosInstance) {
        this.axiosInstance = axiosInstance;
    }

    request<T>(axiosResquestConfig: AxiosRequestConfig): Promise<T> {
        return this.axiosInstance.request<T, AxiosResponse<T>>(axiosResquestConfig).then((r: AxiosResponse<T>) => r.data);
    }

    get<T>(config: AxiosRequestConfig): Promise<T> {
        return this.request<T>({method: 'GET', ...config});
    }

    post<T>(config: AxiosRequestConfig): Promise<T> {
        return this.request<T>({method: 'POST', ...config});
    }

    put<T>(config: AxiosRequestConfig): Promise<T> {
        return this.request<T>({method: 'PUT', ...config});
    }

    patch<T>(config: AxiosRequestConfig): Promise<T> {
        return this.request<T>({method: 'PATCH', ...config});
    }

    delete<T>(config: AxiosRequestConfig): Promise<T> {
        return this.request<T>({method: 'DELETE', ...config});
    }
}

export default AxiosHttpClient;