import {Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle} from "@mui/material";

interface RemoveDialogProps {
    isOpen: boolean;
    onClose: () => void;
    handleRemove: () => void;
}

export default function RemoveDialog({isOpen, onClose, handleRemove}: RemoveDialogProps) {
    return (
        <Dialog open={isOpen} onClose={onClose}>
            <DialogTitle>
                Remover Item
            </DialogTitle>
            <DialogContent>
                <DialogContentText>
                    O item de selecionado será deletado e não será possível desfazer remoção, deseja proseguir ?
                </DialogContentText>
            </DialogContent>
            <DialogActions>
                <Button onClick={handleRemove}>Sim</Button>
                <Button autoFocus onClick={onClose} variant={"contained"}>
                    Não
                </Button>
            </DialogActions>
        </Dialog>
    );
}