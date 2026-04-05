import {Card, CardActionArea, CardActions, CardContent} from "@mui/material";
import Typography from "@mui/material/Typography";
import {tarefaService, type TarefaSimple} from "@services/tarefa.service.ts";
import {type SchemaEditTaskType, TarefaDialogEdit} from "@pages/QuadroTarefas/components/TarefaDialogEdit.tsx";
import {useState} from "react";

export default function CardTarefa({data, loadData}: {data: TarefaSimple, loadData: () => Promise<void>}) {
    const [isDialogTarefaEditOpen, setIsDialogTarefaEditOpen] = useState<boolean>(false);
    const idTarefa = data.id;

    const getDateLabel = (): string => {
        if (data.criadoEm && data.prazo)
            return `${data.criadoEm} - ${data.prazo}`;
        else return data.criadoEm;
    }

    const onEditTarefa = async (data: SchemaEditTaskType) => {
        setIsDialogTarefaEditOpen(false);
        await tarefaService.atualizarTarefa({
            ...data,
            id: idTarefa
        });
        await loadData();
    }

    const handleRemove = async () => {
        if (!idTarefa) return;
        await tarefaService.removerTarefa(idTarefa);
        setIsDialogTarefaEditOpen(false);
        await loadData();
    }

    return (
        <>
            <Card sx={{
                border: ".01rem solid #cacaca",
                mb: "1rem"
            }}>
                <CardActionArea onClick={() => setIsDialogTarefaEditOpen(true)}>
                    <CardContent sx={{
                        padding: "10px 10px 0px 10px",
                        '&:last-child': { paddingBottom: '10px' },
                    }}>
                        <Typography>
                            {data.titulo}
                        </Typography>
                    </CardContent>
                    <CardActions>
                        <Typography variant="body2">
                            {getDateLabel()}
                        </Typography>
                    </CardActions>
                </CardActionArea>
            </Card>
            <TarefaDialogEdit
                open={isDialogTarefaEditOpen}
                onClose={() => setIsDialogTarefaEditOpen(false)}
                idTarefa={data.id}
                onSubmit={onEditTarefa}
                onDelete={handleRemove}
            />
        </>
    );
}