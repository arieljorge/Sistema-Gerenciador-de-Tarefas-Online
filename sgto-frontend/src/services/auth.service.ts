import AxiosHttpClient from "../configs/AxiosHttpClient.ts";
import type {ResponseAPI} from "../types/pagination.ts";
import type {AxiosRequestConfig} from "axios";
import {AxiosPublicInstance} from "../configs/AxiosInstance.ts";

export interface LoginRequest {
    username: string;
    senha: string;
}

export interface LoginResponse {
    token: string;
}

class AuthService extends AxiosHttpClient {
    autenticar(data: LoginRequest): Promise<ResponseAPI<LoginResponse>> {
        return this.post({
            url: '/auth',
            data
        } as AxiosRequestConfig)
    }

    cadastrarUsuario(data: UsuarioCreate): Promise<ResponseAPI<void>> {
        return this.post({
            url: '/usuario',
            data
        } as AxiosRequestConfig)
    }
}

export interface UsuarioCreate {
    username: string;
    email: string;
    senha: string;
}

export const authService = new AuthService(AxiosPublicInstance);