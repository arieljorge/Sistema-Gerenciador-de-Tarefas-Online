import type {UsuarioLoginModel} from "@models/usuario.model.ts";

export interface TokenPayload {
    sub: string;
    name: string;
    roles: string[];
    exp: number;
    iat: number;
}

export interface AuthState {
    token: string;
    user: TokenPayload;
}

export interface AuthContextType {
    auth: AuthState | null;
    login: (token: string, user: UsuarioLoginModel) => void;
    logout: () => void;
    isAuthenticated: boolean;
    hasRole: (role: string) => boolean;
}