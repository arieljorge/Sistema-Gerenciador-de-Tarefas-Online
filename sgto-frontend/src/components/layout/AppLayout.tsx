import {Fragment, type ReactNode} from "react";
import {CssBaseline} from "@mui/material";
import Box from "@mui/material/Box";
import SideMenu from "@components/layout/SideMenu.tsx";
import {alpha} from "@mui/material/styles"
import AppNavbar from "@components/layout/AppNavbar.tsx";

export default function AppLayout({children}: {children: ReactNode}) {
    return (
        <Fragment>
            <CssBaseline enableColorScheme />
            <Box sx={{ display: 'flex' }}>
                <SideMenu/>
                <AppNavbar/>
                <Box
                    component="main"
                    sx={(theme) => ({
                        flexGrow: 1,
                        backgroundColor: theme.vars
                            ? `rgba(${theme.vars.palette.background.defaultChannel} / 1)`
                            : alpha(theme.palette.background.default, 1),
                        overflow: 'auto',
                    })}
                >
                    {children}
                </Box>
            </Box>
        </Fragment>
    );
}