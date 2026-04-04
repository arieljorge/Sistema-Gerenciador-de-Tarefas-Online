import {Controller, useForm} from "react-hook-form";
import {zodResolver} from "@hookform/resolvers/zod";
import z from "zod";
import {
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    FormHelperText,
} from "@mui/material";
import Button from "@mui/material/Button";
import FormLabel from "@mui/material/FormLabel";
import TextField from "@mui/material/TextField";
import FormControl from "@mui/material/FormControl";
import {encadernacaoService} from "@services/encadernacao.service.ts";
import {useEffect} from "react";

interface EditFormProps {
    isOpen: boolean;
    onClose: () => void;
    loadData: () => Promise<void>;
    id?: number;
}

const schema = z.object({
    nome: z.string(),
    idExterno: z.string().nullable().optional().transform(val => val ?? "")
});

type SchemaForm = {
    nome: string;
    idExterno?: string | null;
}

export default function EditForm({isOpen, onClose, loadData, id}: EditFormProps) {
    const {control, reset, handleSubmit, formState: {errors, isSubmitting}} = useForm<SchemaForm>({
        resolver: zodResolver(schema),
        defaultValues: {
            nome: "",
            idExterno: ""
        }
    });

    const saveForm = async (data: SchemaForm) => {
        if (id) {
            await encadernacaoService.atualizarEncadernacao({
                id: id,
                nome: data.nome,
                idExterno: data.idExterno ?? ""
            });
        }

        reset({
            nome: "",
            idExterno: ""
        });
        await loadData();
        onClose();
    }

    useEffect(() => {
        if (!id) return;
        encadernacaoService.obterEncadernacao(id).then(response => {
            reset({
                nome: response.data.nome,
                idExterno: response.data.idExterno,
            });
        })
    }, [id, reset])

    return (
        <Dialog open={isOpen} onClose={onClose}>
            <DialogTitle>Editar Encadernação</DialogTitle>
            <DialogContent>
                <form onSubmit={handleSubmit(saveForm)} id="subscription-form" style={{
                    padding: "1rem",
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
                    <FormControl>
                        <FormLabel htmlFor="idExterno">ID Externo</FormLabel>
                        <Controller
                            name={"idExterno"}
                            control={control}
                            render={({ field }) => (
                                <>
                                    <TextField
                                        {...field}
                                        id={"idExterno"}
                                        type="text"
                                        autoFocus
                                        fullWidth
                                        variant="outlined"
                                        size={"small"}
                                        error={!!errors.idExterno}
                                    />
                                    {errors.idExterno && (
                                        <FormHelperText error>
                                            {errors.idExterno.message}
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
    );
}