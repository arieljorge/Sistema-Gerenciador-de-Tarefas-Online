import {privateRoutes, publicRoutes} from "@routes/routes.config.tsx";
import {BrowserRouter, Routes, Route} from "react-router-dom";
import {Suspense} from "react";
import {PublicRoute} from "./PublicRoute.tsx";
import {PrivateRoute} from "@routes/PrivateRoute.tsx";
import {NotFoundRedirect} from "@routes/NotFoundRedirect.tsx";
import {CircularProgress} from "@mui/material";
import Box from "@mui/material/Box";

function groupByRole(routes: typeof privateRoutes) {
    const groups = new Map<string, typeof  privateRoutes>();

    routes.forEach((route) => {
        const key = route.roles ? route.roles.join(',') : '__authenticated__';
        if (!groups.has(key)) groups.set(key, []);
        groups.get(key)!.push(route);
    })

    return groups;
}

const PageLoader = () => (
    <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100%' }}>
        <CircularProgress size={32}/>
    </Box>
)

export function AppRoutes() {
    const groups = groupByRole(privateRoutes);

    return (
        <BrowserRouter>
            <Suspense fallback={<PageLoader/>}>
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
                    <Route path={"*"} element={<NotFoundRedirect/>}/>
                </Routes>
            </Suspense>
        </BrowserRouter>
    );
}