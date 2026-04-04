import {useAuth} from "@contexts/AuthContext.tsx";
import {Navigate, Outlet, useLocation} from "react-router-dom";
import AppLayout from "@components/layout/AppLayout.tsx";

export function PublicRoute() {
    const {isAuthenticated} = useAuth();
    const location = useLocation();

    if (isAuthenticated) {
        const destination = location.state?.from?.pathname || "/dashboard";
        return <Navigate to={destination} replace/>;
    }

    // remover applayout
    return (
        <AppLayout>
            <Outlet/>
        </AppLayout>
    );
}