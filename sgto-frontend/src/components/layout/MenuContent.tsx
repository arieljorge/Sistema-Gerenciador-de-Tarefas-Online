import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Stack from '@mui/material/Stack';
import DashboardIcon from '@mui/icons-material/Dashboard';
import SettingsIcon from '@mui/icons-material/Settings';
import DataUsageIcon from '@mui/icons-material/DataUsage';
import {NavLink} from "react-router-dom";
import type {ReactNode} from "react";

const mainListItems: MenuOption[] = [
    { text: 'Quadro de Tarefas', icon: <DashboardIcon/>, path: "/"},
    { text: 'Encadernação', icon: <DataUsageIcon/>, path: "/encadernacao"}
];

const secondaryListItems: MenuOption[] = [
    { text: 'Settings', icon: <SettingsIcon/>, path: ""}
];

type MenuOption = {
    text: string;
    icon: ReactNode;
    path: string;
}

export default function MenuContent() {
    return (
        <Stack sx={{ flexGrow: 1, p: 1, justifyContent: 'space-between' }}>
            <List dense>
                {mainListItems.map((item, index) => (
                    <ListItem key={index} disablePadding sx={{ display: 'block' }}>
                        <ListItemButton component={NavLink} to={item.path} sx={{
                            '&.active': {
                                bgcolor: 'primary.light',
                                color: 'white',
                                borderRadius: 1,
                                '& .MuiListItemIcon-root': {
                                    color: 'white',
                                },
                            }
                        }}>
                            <ListItemIcon>{item.icon}</ListItemIcon>
                            <ListItemText primary={item.text} />
                        </ListItemButton>
                    </ListItem>
                ))}
            </List>
            <List dense>
                {secondaryListItems.map((item, index) => (
                    <ListItem key={index} disablePadding sx={{ display: 'block' }}>
                        <ListItemButton component={NavLink} to={item.path} sx={{
                            '&.active': {
                                bgcolor: 'primary.light',
                                color: 'white',
                                borderRadius: 1,
                                '& .MuiListItemIcon-root': {
                                    color: 'white',
                                },
                            }
                        }}>
                            <ListItemIcon>{item.icon}</ListItemIcon>
                            <ListItemText primary={item.text} />
                        </ListItemButton>
                    </ListItem>
                ))}
            </List>
        </Stack>
    );
}