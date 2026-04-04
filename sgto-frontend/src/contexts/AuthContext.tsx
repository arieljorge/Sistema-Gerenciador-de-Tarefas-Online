import {type ReactNode, useState} from "react";
import type {AuthState, TokenPayload} from "../types/auth.ts";
import {jwtDecode} from "jwt-decode";
import { AuthContext } from "@/hooks/useAuth.ts";

function buildAuthState(token: string): AuthState {
    const user = jwtDecode<TokenPayload>(token);
    return {token, user};
}

export function AuthProvider({ children }: { children: ReactNode }) {
    const [auth, setAuth] = useState<AuthState | null>(() => {
        const token = localStorage.getItem('token');
        if (!token) return null;

        try {
            return buildAuthState(token);
        } catch {
            localStorage.removeItem('token');
            return null;
        }
    });

    const login = (token: string) => {
        localStorage.setItem('token', token);
        setAuth(buildAuthState(token))
    }

    const logout = () => {
        localStorage.removeItem('token');
        setAuth(null);
    }

    const hasRole = (role: string): boolean => {
        return auth?.user.roles.includes(role) ?? false;
    }

    return (
        <AuthContext.Provider value={{auth, login, logout, isAuthenticated: !!auth, hasRole}}>
            {children}
        </AuthContext.Provider>
    );
}