import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import {AuthProvider} from "./contexts/AuthContext.tsx";
import {AppRoutes} from "./routes/AppRoutes.tsx";
import {LocalizationProvider} from "@mui/x-date-pickers";
import {AdapterDayjs} from "@mui/x-date-pickers/AdapterDayjs";

createRoot(document.getElementById('root')!).render(
  <StrictMode>
      <LocalizationProvider dateAdapter={AdapterDayjs}>
          <AuthProvider>
              <AppRoutes />
          </AuthProvider>
      </LocalizationProvider>
  </StrictMode>,
)
