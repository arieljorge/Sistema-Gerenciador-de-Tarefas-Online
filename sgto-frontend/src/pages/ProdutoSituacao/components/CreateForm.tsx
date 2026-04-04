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
import {produtoSituacaoService} from "@services/produto.situacao.service.ts";

interface CreateFormProps {
    isOpen: boolean;
    onClose: () => void;
    loadData: () => Promise<void>;
    plataformas: string[];
}

const schema = z.object({
    nome: z.string(),
    idExterno: z.string().nullable().optional().transform(val => val ?? ""),
    plataformaOrigem: z.string()
});

type SchemaForm = {
    nome: string;
    idExterno?: string | null;
    plataformaOrigem: string;
}

export default function CreateForm({isOpen, onClose, loadData, plataformas}: CreateFormProps) {
    const {control, reset, handleSubmit, formState: {errors, isSubmitting}} = useForm<SchemaForm>({
        resolver: zodResolver(schema),
        defaultValues: {
            nome: "",
            idExterno: "",
            plataformaOrigem: ""
        }
    });

    const saveForm = async (data: SchemaForm) => {
        await produtoSituacaoService.salvarProdutoSituacao({
            nome: data.nome,
            idExterno: data.idExterno ?? "",
            plataformaOrigem: data.plataformaOrigem
        });
        reset({
            nome: "",
            idExterno: "",
            plataformaOrigem: ""
        });
        await loadData();
        onClose();
    }

    return (
        <Dialog open={isOpen} onClose={onClose}>
            <DialogTitle>Cadastrar Produto Situação</DialogTitle>
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