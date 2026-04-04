import AxiosHttpClient from "@configs/AxiosHttpClient.ts";
import {AxiosAuthInstance} from "@configs/AxiosInstance.ts";
import type {AxiosRequestConfig} from "axios";
import type {Pagination, ResponseAPI} from "@/types/pagination.ts";

const BASE_PATH = "/contribuicao";

class ContribuicaoService extends AxiosHttpClient{
    salvarContribuicao(data: Contribuicao): Promise<ResponseAPI<void>> {
        return this.post({
            url: BASE_PATH,
            data
        } as AxiosRequestConfig)
    }

    atualizarContribuicao(data: ContribuicaoEdit): Promise<ResponseAPI<void>> {
        return this.put({
            url: BASE_PATH,
            data
        } as AxiosRequestConfig)
    }

    obterContribuicoes(plataforma?: string, page?: number, size?: number): Promise<ResponseAPI<Pagination<Contribuicao>>> {
        return this.get({
            url: BASE_PATH,
            params: {plataforma, page: page || 0, size: size || 30}
        } as AxiosRequestConfig);
    }
    
    obterContribuicao(idContribuicao: number): Promise<ResponseAPI<Contribuicao>> {
        return this.get({
            url: `${BASE_PATH}/${idContribuicao}`
        } as AxiosRequestConfig);
    }

    deletarContribuicao(idContribuicao: number): Promise<ResponseAPI<void>> {
        return this.delete({
            url: `${BASE_PATH}/${idContribuicao}`
        } as AxiosRequestConfig);
    }
}

export interface Contribuicao {
    id?: number;
    nome: string;
    idExterno?: string;
    plataformaOrigem: string;
}

export interface ContribuicaoEdit {
    id: number;
    nome: string;
    idExterno?: string;
}

export const contribuicaoService = new ContribuicaoService(AxiosAuthInstance);