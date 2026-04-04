import {privateRoutes, publicRoutes} from "@routes/routes.config.tsx";
import {BrowserRouter, Routes, Route} from "react-router-dom";
import {Suspense} from "react";
import {PublicRoute} from "./PublicRoute.tsx";
import {PrivateRoute} from "@routes/PrivateRoute.tsx";

function groupByRole(routes: typeof privateRoutes) {
    const groups = new Map<string, typeof  privateRoutes>();

    routes.forEach((route) => {
        const key = route.roles ? route.roles.join(',') : '__authenticated__';
        if (!groups.has(key)) groups.set(key, []);
        groups.get(key)!.push(route);
    })

    return groups;
}

export function AppRoutes() {
    const groups = groupByRole(privateRoutes);

    return (
        <BrowserRouter>
            <Suspense fallback={<div>Carregando...</div>}>
                <Routes>
                    <Route element={<PublicRoute/>}>
                        {publicRoutes.map(({path, component: Component}) => (
                            <Route key={path} path={path} element={<Component/>} />
                        ))}
                    </Route>
                    {Array.from(groups.entries()).map(([key, routes]) => {
                        const roles = key === "__authenticated__" ? undefined : key.split(",");

                        return (
                            <Route key={key} element={<PrivateRoute roles={roles}/>}>
                                {routes.map(({path, component: Component}) => (
                                    <Route key={path} path={path} element={<Component/>} />
                                ))}
                            </Route>
                        );
                    })}
                </Routes>
            </Suspense>
        </BrowserRouter>
    );
}