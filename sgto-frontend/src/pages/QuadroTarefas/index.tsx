import Button from "@mui/material/Button";
import AddIcon from "@mui/icons-material/Add";
import PageContainer from "@components/PageContainer.tsx";
import ListaTarefa from "@pages/QuadroTarefas/components/ListaTarefa.tsx";
import Stack from "@mui/material/Stack";
import {useCallback, useEffect, useState} from "react";
import {type Quadro, quadroService} from "@services/quadro.service.ts";
import {Dialog, DialogActions, DialogContent, DialogTitle, FormHelperText} from "@mui/material";
import FormControl from "@mui/material/FormControl";
import FormLabel from "@mui/material/FormLabel";
import {Controller, useForm} from "react-hook-form";
import TextField from "@mui/material/TextField";
import {zodResolver} from "@hookform/resolvers/zod";
import z from "zod";

const schema = z.object({
    nome: z.string()
});

type SchemaForm = {
    nome: string;
}

export default function QuadroTarefas() {
    const [quadros, setQuadros] = useState<Quadro[]>([]);
    const [isOpen, setIsOpen] = useState<boolean>(false);

    const onOpen = () => {
        setIsOpen(true);
    }

    const onClose = () => {
        setIsOpen(false);
    }

    const loadData = useCallback(async () => {
        quadroService.obterQuadros().then(response => {
            setQuadros(response.data);
        });
    }, [setQuadros]);

    const onRemoveList = async (id: number) => {
        await quadroService.removerQuadro(id);
        await loadData();
    }

    useEffect(() => {
        loadData();
    }, []);

    const {control, reset, handleSubmit, formState: {errors, isSubmitting}} = useForm<SchemaForm>({
        resolver: zodResolver(schema),
        defaultValues: {
            nome: "",
        }
    });

    const saveForm = async (data: SchemaForm) => {
        await quadroService.cadastrarQuadro(data.nome);
        reset({nome: ""});
        await loadData();
        onClose();
    }

    return (
        <PageContainer
            title={"Quadro de Tarefas"}
            actions={
                <Button variant="contained" onClick={onOpen} startIcon={<AddIcon/>}>
                    Criar Novo
                </Button>
            }
        >
            <Stack direction={{ xs: 'column', md: 'row' }} sx={{
                boxShadow: "inset 0px 0px 5px rgba(0,0,0,0.2);",
                height: "85dvh",
                borderRadius: "10px",
                padding: "10px",
                gap: "10px",
                alignItems: "flex-start",
                overflowX: { xs: "hidden", md: "auto"},
                overflowY: { xs: "auto", md: "hidden"},
            }}>
                {quadros.map((value, index) => (
                    <ListaTarefa key={index} quadro={value} onRemove={onRemoveList} reloadLista={loadData}/>
                ))}
            </Stack>
            <Dialog open={isOpen} onClose={onClose}>
                <DialogTitle>Cadastrar Quadro de Tarefas</DialogTitle>
                <DialogContent>
                    <form onSubmit={handleSubmit(saveForm)} id="subscription-form" style={{
                        display: 'flex',
                        flexDirection: 'column',
                        width: '100%',
                        gap: 15,
                    }}>
                        <FormControl>
                            <FormLabel htmlFor="nome">Nome</FormLabel>
                            <Controller
                                name={"nome"}
                                control={control}
                                render={({ field }) => (
                                    <>
                                        <TextField
                                            {...field}
                                            id={"nome"}
                                            type="text"
                                            autoFocus
                                            fullWidth
                                            variant="outlined"
                                            size={"small"}
                                            error={!!errors.nome}
                                        />
                                        {errors.nome && (
                                            <FormHelperText error>
                                                {errors.nome.message}
                                            </FormHelperText>
                                        )}
                                    </>
                                )}
                            />
                        </FormControl>
                    </form>
                </DialogContent>
                <DialogActions>
                    <Button onClick={onClose}>Cancelar</Button>
                    <Button disabled={isSubmitting} type="submit" form="subscription-form" variant={"contained"}>
                        Salvar
                    </Button>
                </DialogActions>
            </Dialog>
        </PageContainer>
    );
}