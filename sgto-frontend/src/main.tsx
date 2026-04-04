import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import {AuthProvider} from "./contexts/AuthContext.tsx";
import {AppRoutes} from "./routes/AppRoutes.tsx";

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <AuthProvider>
        <AppRoutes/>
    </AuthProvider>
  </StrictMode>,
)
