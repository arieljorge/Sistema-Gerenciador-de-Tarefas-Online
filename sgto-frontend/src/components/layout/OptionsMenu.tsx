import LogoutIcon from '@mui/icons-material/Logout';
import IconButton from "@mui/material/IconButton";
import {useAuth} from "@contexts/AuthContext.tsx";

export default function OptionsMenu() {
    const {logout} = useAuth();

    const handleClick = () => {
        logout();
    };

    return (
        <IconButton
            aria-label="Open menu"
            onClick={handleClick}
            sx={{ borderColor: 'transparent' }}
        >
            <LogoutIcon />
        </IconButton>
    );
}