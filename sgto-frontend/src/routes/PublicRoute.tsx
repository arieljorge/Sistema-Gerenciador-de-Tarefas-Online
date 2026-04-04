import {useAuth} from "@hooks/useAuth";
import {Navigate, Outlet, useLocation} from "react-router-dom";

export function PublicRoute() {
    const {isAuthenticated} = useAuth();
    const location = useLocation();

    if (isAuthenticated) {
        const destination = location.state?.from?.pathname || "/";
        return <Navigate to={destination} replace/>;
    }

    return <Outlet/>
}