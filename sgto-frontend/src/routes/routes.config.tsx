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
    }
];

export const privateRoutes: RouteConfig[] = [
    {
        path: "/",
        component: lazy(() => import("@pages/Login"))
    },
    {
        path: "/encadernacao",
        component: lazy(() => import("@pages/Encadernacao"))
    }
]