import AxiosHttpClient from "../configs/AxiosHttpClient.ts";
import type {ResponseAPI} from "../types/pagination.ts";
import type {AxiosRequestConfig} from "axios";
import {AxiosPublicInstance} from "../configs/AxiosInstance.ts";

interface LoginRequest {
    username: string;
    password: string;
}

interface LoginResponse {
    token: string;
}

class AuthService extends AxiosHttpClient {
    login(data: LoginRequest): Promise<ResponseAPI<LoginResponse>> {
        return this.post({
            url: '/auth',
            data
        } as AxiosRequestConfig)
    }
}

export const authService = new AuthService(AxiosPublicInstance);