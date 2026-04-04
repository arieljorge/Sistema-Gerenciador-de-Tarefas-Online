import Button from '@mui/material/Button';
import AddIcon from '@mui/icons-material/Add';
import PageContainer from "@components/PageContainer";
import {Paper, Tab, Tabs} from "@mui/material";
import Box from "@mui/material/Box";
import {type SyntheticEvent, useEffect, useState} from "react";
import {plataformaService} from "@services/plataforma.service.ts";
import {DataGrid, type GridColDef, type GridPaginationModel} from "@mui/x-data-grid";
import IconButton from "@mui/material/IconButton";
import DeleteForeverIcon from '@mui/icons-material/DeleteForever';
import EditIcon from '@mui/icons-material/Edit';
import {type ProdutoSituacao, produtoSituacaoService} from "@services/produto.situacao.service.ts";
import RemoveDialog from "@pages/ProdutoSituacao/components/RemoveDialog.tsx";
import CreateForm from "@pages/ProdutoSituacao/components/CreateForm.tsx";
import EditForm from "@pages/ProdutoSituacao/components/EditForm.tsx";

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

type SearchParams = {
    page: number;
    pageSize: number;
}

export default function ProdutoSituacao() {
    const defaultSearchParams: SearchParams = {
        page: 0,
        pageSize: 25
    }

    const [tabValue, setTabValueValue] = useState(0);
    const [plataformas, setPlataformas] = useState<string[]>(["Padrão"]);
    const [produtoSituacoes, setProdutoSituacoes] = useState<ProdutoSituacao[]>([]);
    const [rowCount, setRowCount] = useState<number>(0);
    const [searchParams, setSearchParams] = useState<SearchParams>(defaultSearchParams);
    const [contextId, setContextId] = useState<number | undefined>();

    const [isRemoveDialogOpen, setIsRemoveDialogOpen] = useState<boolean>(false);
    const [isCreateDialogOpen, setIsCreateDialogOpen] = useState<boolean>(false);
    const [isEditDialogOpen, setIsEditDialogOpen] = useState<boolean>(false);

    const handleChange = (_: SyntheticEvent, newValue: number) => {
        setSearchParams(defaultSearchParams);
        loadData(plataformas[newValue]);
        setTabValueValue(newValue);
    };

    const loadData = async (plataforma?: string, page?: number, size?: number) => {
        try {
            const response = await produtoSituacaoService.obterProdutoSituacoes(
                plataforma || plataformas[0], page ?? searchParams.page, size ?? searchParams.pageSize
            );
            setSearchParams({page: response.data.page, pageSize: response.data.size});
            setProdutoSituacoes(response.data.content);
            setRowCount(response.data.totalElements);
        } catch (err) {
            console.error(err);
        }
    }

    useEffect(() => {
        (async () => {
            try {
                const plataformaServiceResponse = await plataformaService.obterPlataformas();
                const palataformasData = plataformaServiceResponse.data;

                const produtoSituacaoServiceResponse = await produtoSituacaoService.obterProdutoSituacoes(palataformasData[0], 0, 25);

                setPlataformas(palataformasData);
                setProdutoSituacoes(produtoSituacaoServiceResponse.data.content);
                setRowCount(produtoSituacaoServiceResponse.data.totalElements);
            } catch (err) {
                console.error(err);
            }
        })();
    }, []);

    const handlePaginationModelChange = (model: GridPaginationModel) => {
        setSearchParams({
            page: model.page,
            pageSize: model.pageSize,
        });
        loadData(plataformas[tabValue], model.page, model.pageSize);
    }

    const handleCreateButtonClick = () => {
        setIsCreateDialogOpen(true);
    }

    const handleEditButtonClick = (id: number) => {
        setContextId(id);
        setIsEditDialogOpen(true);
    }

    const handleRemoveButtonClick = (id: number) => {
        setContextId(id);
        setIsRemoveDialogOpen(true);
    }

    const handleRemove = async () => {
        if (!contextId) return;
        await produtoSituacaoService.deletarProdutoSituacao(contextId);
        setIsRemoveDialogOpen(false);
        setContextId(undefined);
        await loadData(plataformas[tabValue], defaultSearchParams.page, defaultSearchParams.pageSize);
    }

    const columns: GridColDef[] = [
        { field: 'id', headerName: 'ID', width: 60, sortable: false },
        { field: 'plataformaOrigem', headerName: 'PLATAFORMA', width: 120, sortable: false },
        { field: 'idExterno', headerName: 'ID EXTERNO', width: 120, sortable: false },
        { field: 'nome', headerName: 'NOME', width: 200, sortable: false },
        {
            field: 'actions',
            type: 'actions',
            flex: 1,
            align: 'right',
            minWidth: 100,
            getActions: ({row}) => [
                <IconButton onClick={() => handleEditButtonClick(row.id)}>
                    <EditIcon/>
                </IconButton>,
                <IconButton onClick={() => handleRemoveButtonClick(row.id)}>
                    <DeleteForeverIcon/>
                </IconButton>
            ]
        }
    ]

    return (
        <PageContainer
            title={"Produto Situações"}
            actions={
                <Button variant="contained" onClick={handleCreateButtonClick} startIcon={<AddIcon/>}>
                    Create
                </Button>
            }
        >
            <Box sx={{ width: '100%' }}>
                <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
                    <Tabs value={tabValue} onChange={handleChange}>
                        {plataformas.map((value, index) => (
                            <Tab key={index} label={value} {...a11yProps(index)}/>
                        ))}
                    </Tabs>
                </Box>
                {plataformas.map((_, index) => (
                    <CustomTabPanel value={tabValue} index={index} key={index}>
                        <Paper sx={{ width: '100%', overflow: 'hidden' }}>
                            <DataGrid
                                rows={produtoSituacoes}
                                columns={columns}
                                pagination
                                paginationMode={"server"}
                                rowCount={rowCount}
                                paginationModel={{ page: searchParams.page, pageSize: searchParams.pageSize }}
                                onPaginationModelChange={handlePaginationModelChange}
                                pageSizeOptions={[1, 5, 10, 25, 50]}
                                sx={{ border: 0 }}
                                rowSelection
                                disableRowSelectionOnClick
                                disableColumnMenu
                                disableColumnSelector
                                disableColumnFilter
                            />
                        </Paper>
                    </CustomTabPanel>
                ))}
            </Box>
            <RemoveDialog isOpen={isRemoveDialogOpen} onClose={() => {
                setContextId(undefined);
                setIsRemoveDialogOpen(false);
            }} handleRemove={handleRemove}/>
            <CreateForm isOpen={isCreateDialogOpen} onClose={() => setIsCreateDialogOpen(false)} loadData={() => loadData(plataformas[tabValue], defaultSearchParams.page, defaultSearchParams.pageSize)} plataformas={plataformas}/>
            <EditForm id={contextId} isOpen={isEditDialogOpen} onClose={() => {
                setContextId(undefined);
                setIsEditDialogOpen(false);
            }} loadData={() => loadData(plataformas[tabValue], defaultSearchParams.page, defaultSearchParams.pageSize)}/>
        </PageContainer>
    );
}