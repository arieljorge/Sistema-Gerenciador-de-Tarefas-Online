import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import Stack from '@mui/material/Stack';
import Tooltip from '@mui/material/Tooltip';
import AddIcon from '@mui/icons-material/Add';
import RefreshIcon from '@mui/icons-material/Refresh';
import PageContainer from "@components/PageContainer";
import {Paper, Tab, Table, TableBody, TableCell, TableContainer, TableHead, TablePagination, TableRow, Tabs} from "@mui/material";
import Box from "@mui/material/Box";
import {type SyntheticEvent, useEffect, useState} from "react";
import {plataformaService} from "@services/plataforma.service.ts";

interface TabPanelProps {
    children?: React.ReactNode;
    index: number;
    value: number;
}

function CustomTabPanel(props: TabPanelProps) {
    const { children, value, index, ...other } = props;

    return (
        <div
            role="tabpanel"
            hidden={value !== index}
            id={`simple-tabpanel-${index}`}
            aria-labelledby={`simple-tab-${index}`}
            {...other}
        >
            {value === index && <Box sx={{ p: 3 }}>{children}</Box>}
        </div>
    );
}

function a11yProps(index: number) {
    return {
        id: `simple-tab-${index}`,
        'aria-controls': `simple-tabpanel-${index}`,
    };
}

export default function Encadernacao() {
    const [tabValue, setTabValueValue] = useState(0);
    const [plataformas, setPlataformas] = useState<string[]>(["Padrão"]);

    const handleChange = (event: SyntheticEvent, newValue: number) => {
        setTabValueValue(newValue);
    };

    useEffect(() => {
        plataformaService.obterPlataformas().then(response => {
            setPlataformas(response.data);
        });
    }, [])

    return (
        <PageContainer
            title={"Encadernações"}
            actions={
               <Stack direction="row" alignItems="center" spacing={1}>
                   <Tooltip title="Reload data" placement="right" enterDelay={1000}>
                       <div>
                           <IconButton size="small" aria-label="refresh" onClick={() => {}}>
                               <RefreshIcon />
                           </IconButton>
                       </div>
                   </Tooltip>
                   <Button
                       variant="contained"
                       onClick={() => {}}
                       startIcon={<AddIcon />}
                   >
                       Create
                   </Button>
               </Stack>
            }
        >
            <Box sx={{ width: '100%' }}>
                <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
                    <Tabs value={tabValue} onChange={handleChange}>
                        {plataformas.map((value, index) => (
                            <Tab label={value} {...a11yProps(index)}/>
                        ))}
                    </Tabs>
                </Box>
                {plataformas.map((value, index) => (
                    <CustomTabPanel value={tabValue} index={index}>
                        {value}
                    </CustomTabPanel>
                ))}
            </Box>
            {/*<Paper sx={{ width: '100%', overflow: 'hidden' }}>*/}
            {/*    <TableContainer sx={{ maxHeight: 440 }}>*/}
            {/*        <Table stickyHeader aria-label="sticky table">*/}
            {/*            <TableHead>*/}
            {/*                <TableRow>*/}
            {/*                    <TableCell*/}
            {/*                    >*/}
            {/*                       asd*/}
            {/*                    </TableCell>*/}
            {/*                </TableRow>*/}
            {/*            </TableHead>*/}
            {/*            <TableBody>*/}
            {/*                /!*{rows*!/*/}
            {/*                /!*    .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)*!/*/}
            {/*                /!*    .map((row) => {*!/*/}
            {/*                /!*        return (*!/*/}
            {/*                /!*            <TableRow hover role="checkbox" tabIndex={-1} key={row.code}>*!/*/}
            {/*                /!*                {columns.map((column) => {*!/*/}
            {/*                /!*                    const value = row[column.id];*!/*/}
            {/*                /!*                    return (*!/*/}
            {/*                /!*                        <TableCell key={column.id} align={column.align}>*!/*/}
            {/*                /!*                            {column.format && typeof value === 'number'*!/*/}
            {/*                /!*                                ? column.format(value)*!/*/}
            {/*                /!*                                : value}*!/*/}
            {/*                /!*                        </TableCell>*!/*/}
            {/*                /!*                    );*!/*/}
            {/*                /!*                })}*!/*/}
            {/*                /!*            </TableRow>*!/*/}
            {/*                /!*        );*!/*/}
            {/*                /!*    })}*!/*/}
            {/*            </TableBody>*/}
            {/*        </Table>*/}
            {/*    </TableContainer>*/}
            {/*    /!*<TablePagination*!/*/}
            {/*    /!*    rowsPerPageOptions={[10, 25, 100]}*!/*/}
            {/*    /!*    component="div"*!/*/}
            {/*    /!*    count={rows.length}*!/*/}
            {/*    /!*    rowsPerPage={rowsPerPage}*!/*/}
            {/*    /!*    page={page}*!/*/}
            {/*    /!*    onPageChange={handleChangePage}*!/*/}
            {/*    /!*    onRowsPerPageChange={handleChangeRowsPerPage}*!/*/}
                {/*/>*/}
            {/*</Paper>*/}
        </PageContainer>
    );
}