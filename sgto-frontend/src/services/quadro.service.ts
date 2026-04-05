import AxiosHttpClient from "@configs/AxiosHttpClient.ts";
import {AxiosAuthInstance} from "@configs/AxiosInstance.ts";
import type {ResponseAPI} from "@/types/pagination.ts";
import type {AxiosRequestConfig} from "axios";

const BASE_PATH = "/quadro"

class QuadroService extends AxiosHttpClient {
    obterQuadros(): Promise<ResponseAPI<Quadro[]>> {
        return this.get({
            url: BASE_PATH,
        } as AxiosRequestConfig)
    }

    cadastrarQuadro(nomeQuadro: string): Promise<ResponseAPI<Quadro>> {
        return this.post({
            url: BASE_PATH,
            data: {nome: nomeQuadro}
        } as AxiosRequestConfig)
    }

    removerQuadro(idQuadro: number): Promise<ResponseAPI<void>> {
        return this.delete({
            url: `${BASE_PATH}/${idQuadro}`,
        } as AxiosRequestConfig)
    }
}

export interface Quadro {
    id: number;
    nome: string;
}

export const quadroService = new QuadroService(AxiosAuthInstance);