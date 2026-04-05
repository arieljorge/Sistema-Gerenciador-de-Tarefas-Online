import {lazy} from "react";
import type {ComponentType} from "react";

export interface RouteConfig {
    path: string;
    component: ComponentType;
    roles?: string[];
}

export const publicRoutes: RouteConfig[] = [
    {
        path: "/login",
        component: lazy(() => import("@pages/Login"))
    },
    {
        path: "/cadastro-usuario",
        component: lazy(() => import("@pages/FormularioUsuario"))
    }
];

export const privateRoutes: RouteConfig[] = [
    {
        path: "/",
        component: lazy(() => import("@pages/QuadroTarefas"))
    },
    {
        path: "/encadernacao",
        component: lazy(() => import("@pages/Encadernacao"))
    },
    {
        path: "/contribuicao",
        component: lazy(() => import("@pages/Contribuicao"))
    },
    {
        path: "/produto-situacao",
        component: lazy(() => import("@pages/ProdutoSituacao"))
    }
]