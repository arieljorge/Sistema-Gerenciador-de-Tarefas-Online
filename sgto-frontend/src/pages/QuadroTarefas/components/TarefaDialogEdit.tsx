import {
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    Button,
    TextField,
    MenuItem,
    CircularProgress
} from "@mui/material";
import { useForm, Controller } from "react-hook-form";
import {useEffect, useState} from "react";
import { zodResolver } from "@hookform/resolvers/zod";
import { DatePicker } from "@mui/x-date-pickers";
import dayjs, { type Dayjs } from "dayjs";
import { z } from "zod";
import {tarefaService} from "@services/tarefa.service.ts";
import Divider from "@mui/material/Divider";
import {type SimpleUsuario, usuarioService} from "@services/usuario.service.ts";

const schema = z.object({
    titulo: z.string().min(1, "Título obrigatório"),
    descricao: z.string().optional(),
    prazo: z
        .string()
        .optional()
        .transform((val) => (val === "" ? undefined : val)),
    usuarios: z.array(z.string()).optional()
});

export interface SchemaEditTaskType {
    titulo: string;
    descricao?: string;
    prazo?: string;
    usuarios?: string[];
}

interface Props {
    open: boolean;
    idTarefa: number;
    onClose: () => void;
    onSubmit: (data: SchemaEditTaskType) => Promise<void>;
    onDelete: () => Promise<void>;
}

export function TarefaDialogEdit({open, idTarefa, onClose, onSubmit, onDelete}: Props) {

    const {
        control,
        handleSubmit,
        formState: { errors },
        reset
    } = useForm<SchemaEditTaskType>({
        resolver: zodResolver(schema),
        defaultValues: {
            titulo: ""
        }
    });

    const [loading, setLoading] = useState(false);
    const [usuarios, setUsuarios] = useState<SimpleUsuario[]>([]);

    useEffect(() => {
        if (!open || !idTarefa) return;

        const fetchData = async () => {
            try {
                setLoading(true);

                const tarefa = await tarefaService.obterTarefa(idTarefa);
                const usuarios = await usuarioService.obterUsuarios();

                reset({
                    titulo: tarefa.data.titulo,
                    descricao: tarefa.data.descricao ?? "",
                    prazo: tarefa.data.prazo ?? undefined,
                    usuarios: tarefa.data.usuarios ?? []
                });

                setUsuarios(usuarios.data);
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, [open, idTarefa, reset, setUsuarios]);

    const handleSubmitReset = async (data: SchemaEditTaskType) => {
        await onSubmit(data);
        reset();
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose} fullWidth maxWidth="sm">
            <DialogTitle>Editar Tarefa</DialogTitle>

            <DialogContent sx={{ display: "flex", flexDirection: "column", gap: 2, mt: 1 }}>

                {loading ? (
                    <CircularProgress />
                ) : (
                    <>
                        <Button variant={"outlined"} color={"error"} onClick={onDelete}>
                            Remover
                        </Button>
                        <Divider/>
                        <Controller
                            name="titulo"
                            control={control}
                            render={({ field }) => (
                                <TextField
                                    {...field}
                                    label="Título"
                                    error={!!errors.titulo}
                                    helperText={errors.titulo?.message}
                                    fullWidth
                                />
                            )}
                        />

                        <Controller
                            name="prazo"
                            control={control}
                            render={({ field }) => (
                                <DatePicker
                                    label="Prazo"
                                    value={field.value ? dayjs(field.value) : null}
                                    onChange={(date: Dayjs | null) => {
                                        field.onChange(
                                            date ? date.format("YYYY-MM-DD") : undefined
                                        );
                                    }}
                                    slotProps={{
                                        textField: {
                                            error: !!errors.prazo,
                                            helperText: errors.prazo?.message,
                                            fullWidth: true
                                        }
                                    }}
                                />
                            )}
                        />

                        <Controller
                            name="usuarios"
                            control={control}
                            render={({ field }) => (
                                <TextField
                                    {...field}
                                    select
                                    label="Usuários"
                                    value={field.value ?? []}
                                    onChange={(e) => field.onChange(e.target.value)}
                                    error={!!errors.usuarios}
                                    helperText={errors.usuarios?.message}
                                    fullWidth
                                    slotProps={{
                                        select: { multiple: true }
                                    }}
                                >
                                    {usuarios.map((user) => (
                                        <MenuItem key={user.id} value={user.id}>
                                            {user.nome}
                                        </MenuItem>
                                    ))}
                                </TextField>
                            )}
                        />

                        <Controller
                            name="descricao"
                            control={control}
                            render={({ field }) => (
                                <TextField
                                    {...field}
                                    label="Descrição"
                                    multiline
                                    rows={6}
                                    error={!!errors.descricao}
                                    helperText={errors.descricao?.message}
                                    fullWidth
                                />
                            )}
                        />
                    </>
                )}
            </DialogContent>

            <DialogActions>
                <Button onClick={onClose}>Cancelar</Button>
                <Button
                    onClick={handleSubmit(handleSubmitReset)}
                    variant="contained"
                    disabled={loading}
                >
                    Salvar
                </Button>
            </DialogActions>
        </Dialog>
    );
}