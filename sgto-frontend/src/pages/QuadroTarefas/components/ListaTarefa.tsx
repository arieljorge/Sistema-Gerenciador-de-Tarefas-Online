import Box from "@mui/material/Box";
import CardTarefa from "@pages/QuadroTarefas/components/CardTarefa.tsx";
import CircleIcon from '@mui/icons-material/Circle';
import Typography from "@mui/material/Typography";
import IconButton from "@mui/material/IconButton";
import RemoveIcon from '@mui/icons-material/Remove';
import Button from "@mui/material/Button";
import AddIcon from '@mui/icons-material/Add';
import Divider from "@mui/material/Divider";
import type {Quadro} from "@services/quadro.service.ts";
import {Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle} from "@mui/material";
import {useCallback, useEffect, useState} from "react";
import {tarefaService, type TarefaSimple} from "@services/tarefa.service.ts";
import {
    type SchemaCreateTaskType,
    TarefaDialogCreate
} from "@pages/QuadroTarefas/components/TarefaDialogCreate.tsx";

export default function ListaTarefa({quadro, onRemove, reloadLista}: {quadro: Quadro, onRemove: (id: number) => Promise<void>, reloadLista: () => Promise<void>}) {
    const [isOpen, setIsOpen] = useState<boolean>(false);
    const [loading, setLoading] = useState<boolean>(false);
    const [tarefas, setTarefas] = useState<TarefaSimple[]>([]);

    const [isDialogTarefaCreateOpen, setIsDialogTarefaCreateOpen] = useState<boolean>(false);

    const onOpen = () => {
        setIsOpen(true);
    }

    const onClose = () => {
        setIsOpen(false);
    }

    const handleRemove = async () => {
        try {
            setLoading(true);
            await onRemove(quadro.id);
            setIsOpen(false);
        } catch (err) {
            console.error(err);
        } finally {
            setLoading(false);
        }
    }

    const onCreateTarefa = async (data: SchemaCreateTaskType) => {
        await tarefaService.cadastrarTarefa({
            ...data,
            idQuadro: quadro.id
        });
        await loadData();
    }

    const loadData = useCallback(async () => {
        try {
            const tarefas = await tarefaService.obterTarefas(quadro.id);
            setTarefas(tarefas.data);
        } catch (err) {
            console.error(err);
        }
    }, [quadro])

    useEffect(() => {
        loadData();
    }, [quadro, loadData])

    return (
        <Box sx={{
            borderRadius: "5px",
            minWidth: "20rem",
            padding: ".5rem",
            border: "1px solid #cacaca",
            "@media (max-width: 600px)": {
                width: "100%",
            }
        }}>
            <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between'}}>
                <Typography fontWeight={500} sx={{maxWidth: "300px", whiteSpace: "nowrap", overflow: "hidden", textOverflow: "ellipsis"}}>
                    <CircleIcon sx={{height: "10px"}}/>
                    {quadro.nome}
                </Typography>
                <IconButton onClick={onOpen}>
                    <RemoveIcon/>
                </IconButton>
            </Box>
            <Button startIcon={<AddIcon/>} fullWidth variant={"outlined"} color={"inherit"} sx={{marginTop: "10px"}} onClick={() => setIsDialogTarefaCreateOpen(true)}>
                Adicionar Tarefa
            </Button>
            <Divider sx={{marginY: "15px"}}/>
            <Box sx={{
                padding: ".5rem",
                height: "40rem",
                backgroundColor: "white",
                overflowY: "auto"
            }}>
                {tarefas.map((value, index) => (
                    <CardTarefa key={index} data={value} loadData={reloadLista}/>
                ))}
            </Box>
            <Dialog open={isOpen} onClose={onClose} aria-label={"remove-dialog"}>
                <DialogTitle>
                    Remover Item
                </DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        O item de selecionado será deletado e não será possível desfazer remoção, deseja proseguir ?
                    </DialogContentText>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleRemove} disabled={loading}>Sim</Button>
                    <Button autoFocus onClick={onClose} variant={"contained"} disabled={loading}>
                        Não
                    </Button>
                </DialogActions>
            </Dialog>
            <TarefaDialogCreate open={isDialogTarefaCreateOpen} onClose={() => setIsDialogTarefaCreateOpen(false)} onSubmit={onCreateTarefa}/>
        </Box>
    );
}