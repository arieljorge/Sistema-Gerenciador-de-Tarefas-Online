import {Controller, useForm} from "react-hook-form";
import {zodResolver} from "@hookform/resolvers/zod";
import z from "zod";
import {
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    FormHelperText,
    MenuItem,
    Select
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
    plataformas: string[];
    id?: number;
}

const loginSchema = z.object({
    nome: z.string(),
    idExterno: z.string(),
    plataformaOrigem: z.string()
});

type LoginForm = z.infer<typeof loginSchema>;

export default function EditForm({isOpen, onClose, loadData, plataformas, id}: EditFormProps) {
    const {control, reset, handleSubmit, formState: {errors, isSubmitting}} = useForm<LoginForm>({
        resolver: zodResolver(loginSchema),
        defaultValues: {
            nome: "",
            idExterno: "",
            plataformaOrigem: ""
        }
    });

    const saveForm = async (data: LoginForm) => {
        if (!id) return;
        await encadernacaoService.salvarEncadernacoes([{
            id: id,
            nome: data.nome,
            idExterno: data.idExterno,
            plataformaOrigem: data.plataformaOrigem
        }]);

        reset();
        await loadData();
        onClose();
    }

    useEffect(() => {
        if (!id) return;
        encadernacaoService.obterEncadernacao(id).then(response => {
            reset({
                nome: response.data.nome,
                idExterno: response.data.idExterno,
                plataformaOrigem: response.data.plataformaOrigem
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
                    <FormControl>
                        <FormLabel htmlFor="plataformaOrigem">Plataforma</FormLabel>
                        <Controller
                            name={"plataformaOrigem"}
                            control={control}
                            render={({ field }) => (
                                <>
                                    <Select
                                        {...field}
                                        fullWidth
                                        id={"plataformaOrigem"}
                                        size={"small"}
                                        variant="outlined"
                                        error={!!errors.plataformaOrigem}
                                        value={field.value ?? ""}
                                    >
                                        <MenuItem value="">Selecione...</MenuItem>
                                        {plataformas.map((p) => (
                                            <MenuItem key={p} value={p}>
                                                {p}
                                            </MenuItem>
                                        ))}
                                    </Select>
                                    {errors.plataformaOrigem && (
                                        <FormHelperText error>
                                            {errors.plataformaOrigem.message}
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