import AxiosHttpClient from "@configs/AxiosHttpClient.ts";
import {AxiosAuthInstance} from "@configs/AxiosInstance.ts";
import type {ResponseAPI} from "@/types/pagination.ts";
import type {AxiosRequestConfig} from "axios";

class UsuarioService extends AxiosHttpClient {
    obterUsuarios(): Promise<ResponseAPI<SimpleUsuario[]>> {
        return this.get({
            url: "/usuario"
        } as AxiosRequestConfig);
    }
}

export interface SimpleUsuario {
    id: string;
    nome: string;
}

export const usuarioService = new UsuarioService(AxiosAuthInstance);