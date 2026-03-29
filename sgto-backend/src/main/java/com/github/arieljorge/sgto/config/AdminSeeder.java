package com.github.arieljorge.sgto.config;

import com.github.arieljorge.sgto.entity.Role;
import com.github.arieljorge.sgto.entity.Usuario;
import com.github.arieljorge.sgto.entity.UsuariosRoles;
import com.github.arieljorge.sgto.entity.UsuariosRolesID;
import com.github.arieljorge.sgto.repository.RoleRepository;
import com.github.arieljorge.sgto.repository.UsuarioRepository;
import com.github.arieljorge.sgto.repository.UsuariosRolesRepository;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AdminSeeder implements ApplicationRunner {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final UsuariosRolesRepository usuariosRolesRepository;
    private final PasswordEncoder passwordEncoder;

    @Email
    @NotBlank
    @Size(max = 150)
    @Value("${admin.email}")
    private String adminEmail;

    @NotBlank
    @Size(max = 50)
    @Value("${admin.username}")
    private String adminUsername;

    @NotBlank
    @Size(min = 6)
    @Value("${admin.senha}")
    private String adminSenha;

    @Override
    public void run(@NonNull ApplicationArguments args) {
        if (usuarioRepository.existsByUsername("admin")) {
            return;
        }

        Usuario admin = new Usuario();
        admin.setUsername(adminUsername);
        admin.setEmail(adminEmail);
        admin.setSenhaHash(passwordEncoder.encode(adminSenha));
        admin.setAtivo(true);
        admin.setCriadoEm(LocalDateTime.now());
        admin.setAtualizadoEm(LocalDateTime.now());

        admin = this.usuarioRepository.save(admin);

        atribuirRole(admin, "ROLE_USUARIO");
        atribuirRole(admin, "ROLE_ADMIN");
    }

    private void atribuirRole(Usuario usuario, String nomeRole) {
        Role role = roleRepository.findByNome(nomeRole)
                .orElseThrow(() -> new IllegalStateException("Role não encontrada: " + nomeRole));

        UsuariosRolesID id = new UsuariosRolesID();
        id.setIdUsuario(usuario.getId());
        id.setIdRole(role.getId());

        usuariosRolesRepository.save(new UsuariosRoles(id));
    }
}
