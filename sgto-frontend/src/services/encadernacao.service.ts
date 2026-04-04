import AxiosHttpClient from "@configs/AxiosHttpClient.ts";
import {AxiosAuthInstance} from "@configs/AxiosInstance.ts";
import type {AxiosRequestConfig} from "axios";
import type {Pagination, ResponseAPI} from "@/types/pagination.ts";

const BASE_PATH = "encadernacao";

class EncadernacaoService extends AxiosHttpClient{
    salvarEncadernacoes(data: Encadernacao[]): Promise<ResponseAPI<void>> {
        return this.post({
            url: BASE_PATH,
            data
        } as AxiosRequestConfig)
    }

    obterEncadernacoes(page?: number, size?: number): Promise<ResponseAPI<Pagination<Encadernacao>>> {
        return this.get({
            urls: BASE_PATH,
            params: {page, size}
        } as AxiosRequestConfig);
    }
    
    obterEncadernacao(idEncadernacao: number): Promise<ResponseAPI<Encadernacao>> {
        return this.get({
            url: `${BASE_PATH}/${idEncadernacao}`
        } as AxiosRequestConfig);
    }

    deletarEncadernacao(idEncadernacao: number): Promise<ResponseAPI<void>> {
        return this.delete({
            url: `${BASE_PATH}/${idEncadernacao}`
        } as AxiosRequestConfig);
    }
}

export interface Encadernacao {
    id?: number;
    nome: string;
    idExterno?: string;
    plataformaOrigem: string;
}

export const encadernacaoService = new EncadernacaoService(AxiosAuthInstance);