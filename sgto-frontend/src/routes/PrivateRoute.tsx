import {useAuth} from "@contexts/AuthContext.tsx";
import {Navigate, Outlet, useLocation} from "react-router-dom";
import AppLayout from "@components/layout/AppLayout.tsx";

interface PrivateRouteProps {
    roles?: string[];
}

export function PrivateRoute({roles}: PrivateRouteProps) {
    const {isAuthenticated, hasRole} = useAuth();
    const location = useLocation();

    if (!isAuthenticated) {
        return <Navigate to={"/login"} state={{from: location}} replace/>
    }

    if (roles && !roles.some(hasRole)) {
        return <Navigate to={"/dashboard"} replace/>
    }

    return (
        <AppLayout>
            <Outlet/>
        </AppLayout>
    );
}