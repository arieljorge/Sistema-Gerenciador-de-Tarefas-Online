import AxiosHttpClient from "@configs/AxiosHttpClient.ts";
import type {ResponseAPI} from "@/types/pagination.ts";
import type {AxiosRequestConfig} from "axios";
import {AxiosAuthInstance} from "@configs/AxiosInstance.ts";

class PlataformaService extends AxiosHttpClient {
    obterPlataformas(): Promise<ResponseAPI<string[]>> {
        return this.get({
            url: '/plataforma'
        } as AxiosRequestConfig)
    }
}

export const plataformaService = new PlataformaService(AxiosAuthInstance);