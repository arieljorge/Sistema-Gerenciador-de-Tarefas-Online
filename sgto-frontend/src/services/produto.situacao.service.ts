import AxiosHttpClient from "@configs/AxiosHttpClient.ts";
import {AxiosAuthInstance} from "@configs/AxiosInstance.ts";
import type {AxiosRequestConfig} from "axios";
import type {Pagination, ResponseAPI} from "@/types/pagination.ts";

const BASE_PATH = "/produto-situacao";

class ProdutoSituacaoService extends AxiosHttpClient{
    salvarProdutoSituacao(data: ProdutoSituacao): Promise<ResponseAPI<void>> {
        return this.post({
            url: BASE_PATH,
            data
        } as AxiosRequestConfig)
    }

    atualizarProdutoSituacao(data: ProdutoSituacaoEdit): Promise<ResponseAPI<void>> {
        return this.put({
            url: BASE_PATH,
            data
        } as AxiosRequestConfig)
    }

    obterProdutoSituacoes(plataforma?: string, page?: number, size?: number): Promise<ResponseAPI<Pagination<ProdutoSituacao>>> {
        return this.get({
            url: BASE_PATH,
            params: {plataforma, page: page || 0, size: size || 30}
        } as AxiosRequestConfig);
    }
    
    obterProdutoSituacao(idProdutoSituacao: number): Promise<ResponseAPI<ProdutoSituacao>> {
        return this.get({
            url: `${BASE_PATH}/${idProdutoSituacao}`
        } as AxiosRequestConfig);
    }

    deletarProdutoSituacao(idProdutoSituacao: number): Promise<ResponseAPI<void>> {
        return this.delete({
            url: `${BASE_PATH}/${idProdutoSituacao}`
        } as AxiosRequestConfig);
    }
}

export interface ProdutoSituacao {
    id?: number;
    nome: string;
    idExterno?: string;
    plataformaOrigem: string;
}

export interface ProdutoSituacaoEdit {
    id: number;
    nome: string;
    idExterno?: string;
}

export const produtoSituacaoService = new ProdutoSituacaoService(AxiosAuthInstance);