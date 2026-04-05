import AxiosHttpClient from "@configs/AxiosHttpClient.ts";
import {AxiosAuthInstance} from "@configs/AxiosInstance.ts";
import type {ResponseAPI} from "@/types/pagination.ts";
import type {AxiosRequestConfig} from "axios";

const BASE_PATH = "/tarefa";

class TarefaService extends AxiosHttpClient {
    obterTarefas(idQuadro: number): Promise<ResponseAPI<TarefaSimple[]>> {
        return this.get({
            url: BASE_PATH,
            params: {"idQuadro": idQuadro}
        } as AxiosRequestConfig);
    }

    obterTarefa(id: number): Promise<ResponseAPI<Tarefa>> {
        return this.get({
            url: `${BASE_PATH}/${id}`,
        } as AxiosRequestConfig);
    }

    removerTarefa(id: number): Promise<ResponseAPI<void>> {
        return this.delete({
            url: `${BASE_PATH}/${id}`,
        } as AxiosRequestConfig);
    }

    cadastrarTarefa(data: TarefaCreate): Promise<ResponseAPI<Tarefa>> {
        return this.post({
            url: BASE_PATH,
            data
        } as AxiosRequestConfig);
    }

    atualizarTarefa(data: TarefaEdit): Promise<ResponseAPI<Tarefa>> {
        return this.put({
            url: BASE_PATH,
            data
        } as AxiosRequestConfig);
    }
}

export interface Tarefa {
    id: number;
    idQuadro: number;
    titulo: string;
    descricao?: string;
    prazo?: string;
    criadoEm: string;
    usuarios?: string[];
}

export interface TarefaSimple {
    id: number;
    titulo: string;
    criadoEm: string;
    prazo: string;
}

export interface TarefaCreate {
    idQuadro: number;
    titulo: string;
    descricao?: string;
    prazo?: string;
    usuarios?: string[];
}

export interface TarefaEdit {
    id: number;
    idQuadro: number;
    titulo: string;
    descricao?: string;
    prazo?: string;
    usuarios?: string[];
}

export const tarefaService = new TarefaService(AxiosAuthInstance);