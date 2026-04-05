import {
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    Button,
    TextField,
    MenuItem
} from "@mui/material";
import { useForm, Controller } from "react-hook-form";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import {DatePicker} from "@mui/x-date-pickers";
import dayjs, {type Dayjs} from "dayjs";
import Divider from "@mui/material/Divider";
import {useEffect, useState} from "react";
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

export interface SchemaCreateTaskType {
    titulo: string;
    descricao?: string;
    prazo?: string;
    usuarios?: string[];
}

interface Props {
    open: boolean;
    onClose: () => void;
    onSubmit: (data: SchemaCreateTaskType) => Promise<void>;
}

export function TarefaDialogCreate({open, onClose, onSubmit}: Props) {
    const {
        control,
        handleSubmit,
        formState: { errors },
        reset
    } = useForm<SchemaCreateTaskType>({
        resolver: zodResolver(schema),
        defaultValues: {
            titulo: ""
        }
    });

    const [usuarios, setUsuarios] = useState<SimpleUsuario[]>([]);

    const handleSubmitReset = (data: SchemaCreateTaskType) => {
        onSubmit(data);
        reset();
        onClose();
    }

    const handleCloseReset = () => {
        reset();
        onClose();
    }

    useEffect(() => {
        const fetchData = async () => {
            try {
                const usuarios = await usuarioService.obterUsuarios();
                setUsuarios(usuarios.data);
            } catch (err) {
                console.error(err);
            }
        };

        fetchData();
    }, [setUsuarios])

    return (
        <Dialog open={open} onClose={handleCloseReset} fullWidth maxWidth="sm">
            <DialogTitle>Criar Tarefa</DialogTitle>
            <DialogContent sx={{ display: "flex", flexDirection: "column", gap: 2, mt: 1 }}>
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
                                field.onChange(date ? date.format("YYYY-MM-DD") : undefined);
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
                            value={field.value || []}
                            onChange={(e) => field.onChange(e.target.value)}
                            error={!!errors.usuarios}
                            helperText={errors.usuarios?.message}
                            fullWidth
                            slotProps={{
                                select: {
                                    multiple: true
                                }
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
                            rows={10}
                            error={!!errors.descricao}
                            helperText={errors.descricao?.message}
                            fullWidth
                        />
                    )}
                />
            </DialogContent>
            <DialogActions>
                <Button onClick={handleCloseReset}>
                    Cancelar
                </Button>
                <Button
                    onClick={handleSubmit(handleSubmitReset)}
                    variant="contained"
                >
                    Salvar
                </Button>
            </DialogActions>
        </Dialog>
    );
}