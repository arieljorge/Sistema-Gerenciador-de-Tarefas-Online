import {useAuth} from "@hooks/useAuth.ts";
import {Navigate} from "react-router-dom";

export function NotFoundRedirect() {
    const {isAuthenticated} = useAuth();

    return isAuthenticated
    ? <Navigate to={"/"} replace/>
    : <Navigate to={"/login"} replace/>
}