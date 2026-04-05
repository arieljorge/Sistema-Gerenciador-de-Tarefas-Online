import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import FormLabel from '@mui/material/FormLabel';
import FormControl from '@mui/material/FormControl';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import Stack from '@mui/material/Stack';
import MuiCard from '@mui/material/Card';
import { styled } from '@mui/material/styles';
import {Fragment} from "react";
import {Controller, useForm} from "react-hook-form";
import {z} from "zod";
import {zodResolver} from "@hookform/resolvers/zod";
import {authService, type UsuarioCreate} from "@services/auth.service.ts";
import {useNavigate} from "react-router-dom";
import {Link} from "react-router";

const Card = styled(MuiCard)(({ theme }) => ({
    display: 'flex',
    flexDirection: 'column',
    alignSelf: 'center',
    width: '100%',
    padding: theme.spacing(4),
    gap: theme.spacing(2),
    margin: 'auto',
    [theme.breakpoints.up('sm')]: {
        maxWidth: '380px',
    },
    boxShadow:
        'hsla(220, 30%, 5%, 0.05) 0px 5px 15px 0px, hsla(220, 25%, 10%, 0.05) 0px 15px 35px -5px',
    ...theme.applyStyles('dark', {
        boxShadow:
            'hsla(220, 30%, 5%, 0.5) 0px 5px 15px 0px, hsla(220, 25%, 10%, 0.08) 0px 15px 35px -5px',
    }),
}));

const SignInContainer = styled(Stack)(({ theme }) => ({
    height: 'calc((1 - var(--template-frame-height, 0)) * 100dvh)',
    minHeight: '100%',
    padding: theme.spacing(2),
    [theme.breakpoints.up('sm')]: {
        padding: theme.spacing(4),
    },
    '&::before': {
        content: '""',
        display: 'block',
        position: 'absolute',
        zIndex: -1,
        inset: 0,
        backgroundImage:
            'radial-gradient(ellipse at 50% 50%, hsl(210, 100%, 97%), hsl(0, 0%, 100%))',
        backgroundRepeat: 'no-repeat',
        ...theme.applyStyles('dark', {
            backgroundImage:
                'radial-gradient(at 50% 50%, hsla(210, 100%, 16%, 0.5), hsl(220, 30%, 5%))',
        }),
    },
}));

const loginSchema = z.object({
    usuario: z.string(),
    email: z.string(),
    senha: z.string()
});

type LoginForm = z.infer<typeof loginSchema>;

export default function FormularioUsuario() {
    const navigate = useNavigate();

    const {control, handleSubmit, formState: {errors, isSubmitting}} = useForm<LoginForm>({
        resolver: zodResolver(loginSchema),
        defaultValues: {
            usuario: "",
            email: "",
            senha: ""
        }
    });

    const onSubmit = async (data: LoginForm) => {
        const usuario: UsuarioCreate = {
            username: data.usuario,
            email: data.email,
            senha: data.senha
        }

        try {
            await authService.cadastrarUsuario(usuario);
            navigate('/login');
        } catch (err) {
            console.error(err);
        }
    };

    return (
        <Fragment>
            <CssBaseline enableColorScheme />
            <SignInContainer direction="column" justifyContent="space-between">
                <Card variant="outlined">
                    <Typography
                        component="h2"
                        variant="h1"
                        sx={{ width: '100%', fontSize: 'clamp(2rem, 10vw, 2rem)' }}
                    >
                        Cadastro de Usuário
                    </Typography>
                    <Box
                        component="form"
                        onSubmit={handleSubmit(onSubmit, (errors) => console.error(errors))}
                        noValidate
                        sx={{
                            marginTop: "3vh",
                            display: 'flex',
                            flexDirection: 'column',
                            width: '100%',
                            gap: 3,
                        }}
                    >
                        <FormControl>
                            <FormLabel htmlFor="usuario">Nome de Usuário</FormLabel>
                            <Controller
                                name={"usuario"}
                                control={control}
                                render={({ field }) => (
                                    <TextField
                                        {...field}
                                        id={"usuario"}
                                        type="text"
                                        autoFocus
                                        fullWidth
                                        variant="outlined"
                                        size={"small"}
                                        error={!!errors.usuario}
                                        helperText={errors.usuario?.message}
                                    />
                                )}
                            />
                        </FormControl>
                        <FormControl>
                            <FormLabel htmlFor="email">Email</FormLabel>
                            <Controller
                                name={"email"}
                                control={control}
                                render={({ field }) => (
                                    <TextField
                                        {...field}
                                        id={"email"}
                                        type="email"
                                        fullWidth
                                        variant="outlined"
                                        size={"small"}
                                        error={!!errors.email}
                                        helperText={errors.email?.message}
                                    />
                                )}
                            />
                        </FormControl>
                        <FormControl>
                            <FormLabel htmlFor="senha">Senha</FormLabel>
                            <Controller
                                name={"senha"}
                                control={control}
                                render={({ field }) => (
                                    <TextField
                                        {...field}
                                        id={"senha"}
                                        type="password"
                                        fullWidth
                                        variant="outlined"
                                        size={"small"}
                                        error={!!errors.senha}
                                        helperText={errors.senha?.message}
                                    />
                                )}
                            />
                        </FormControl>
                        <Link to={"/login"}>voltar para a tela de acesso</Link>
                        <Box marginTop={1}>
                            <Button
                                type="submit"
                                fullWidth
                                variant="contained"
                                disabled={isSubmitting}
                            >
                                {isSubmitting ? "Cadastrando..." : "Cadastrar"}
                            </Button>
                        </Box>
                    </Box>
                </Card>
            </SignInContainer>
        </Fragment>
    );
}