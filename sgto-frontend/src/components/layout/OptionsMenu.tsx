import LogoutIcon from '@mui/icons-material/Logout';
import IconButton from "@mui/material/IconButton";
import {useAuth} from "@hooks/useAuth";

export default function OptionsMenu() {
    const {logout} = useAuth();

    const handleClick = () => {
        logout();
    };

    return (
        <IconButton
            aria-label="Logout"
            onClick={handleClick}
            sx={{ borderColor: 'transparent' }}
        >
            <LogoutIcon />
        </IconButton>
    );
}