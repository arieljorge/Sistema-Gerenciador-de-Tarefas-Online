import AxiosHttpClient from "@configs/AxiosHttpClient.ts";
import {AxiosAuthInstance} from "@configs/AxiosInstance.ts";
import type {AxiosRequestConfig} from "axios";
import type {Pagination, ResponseAPI} from "@/types/pagination.ts";

const BASE_PATH = "/encadernacao";

class EncadernacaoService extends AxiosHttpClient{
    salvarEncadernacao(data: Encadernacao): Promise<ResponseAPI<void>> {
        return this.post({
            url: BASE_PATH,
            data
        } as AxiosRequestConfig)
    }

    atualizarEncadernacao(data: EncadernacaoEdit): Promise<ResponseAPI<void>> {
        return this.put({
            url: BASE_PATH,
            data
        } as AxiosRequestConfig)
    }

    obterEncadernacoes(plataforma?: string, page?: number, size?: number): Promise<ResponseAPI<Pagination<Encadernacao>>> {
        return this.get({
            url: BASE_PATH,
            params: {plataforma, page: page || 0, size: size || 30}
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

export interface EncadernacaoEdit {
    id: number;
    nome: string;
    idExterno?: string;
}

export const encadernacaoService = new EncadernacaoService(AxiosAuthInstance);