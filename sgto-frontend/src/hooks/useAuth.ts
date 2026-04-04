import {createContext, useContext} from 'react'
import type {AuthContextType} from "@custom-types/auth.ts";

export const AuthContext = createContext<AuthContextType>({} as AuthContextType);
export const useAuth = () => useContext(AuthContext);