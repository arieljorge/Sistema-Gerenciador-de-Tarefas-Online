package com.github.arieljorge.sgto.service.impl;

import com.github.arieljorge.sgto.dto.UsuarioCreateDto;
import com.github.arieljorge.sgto.dto.UsuarioLoginDto;
import com.github.arieljorge.sgto.dto.UsuarioLoginOutDto;
import com.github.arieljorge.sgto.entity.Role;
import com.github.arieljorge.sgto.entity.Usuario;
import com.github.arieljorge.sgto.entity.UsuariosRoles;
import com.github.arieljorge.sgto.entity.UsuariosRolesID;
import com.github.arieljorge.sgto.repository.RoleRepository;
import com.github.arieljorge.sgto.repository.UsuarioRepository;
import com.github.arieljorge.sgto.repository.UsuariosRolesRepository;
import com.github.arieljorge.sgto.service.JwtService;
import com.github.arieljorge.sgto.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final UsuariosRolesRepository usuariosRolesRepository;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioDetailsService usuarioDetailsService;

    @Override
    @Transactional
    public void cadastrarUsuario(UsuarioCreateDto usuarioCreateDto) {
        if (this.usuarioRepository.existsByUsername(usuarioCreateDto.username())) {
            throw new IllegalArgumentException("Nome de usuário já cadastrado.");
        }

        if (this.usuarioRepository.existsByEmail(usuarioCreateDto.email())) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioCreateDto.username());
        usuario.setEmail(usuarioCreateDto.email());
        usuario.setSenhaHash(passwordEncoder.encode(usuarioCreateDto.senha()));
        usuario.setAtivo(true);
        usuario.setCriadoEm(LocalDateTime.now());
        usuario.setAtualizadoEm(LocalDateTime.now());

        usuario = this.usuarioRepository.save(usuario);

        final Role roleUsuario = this.roleRepository.findByNome("ROLE_USUARIO").orElseThrow(() -> {
            return new IllegalStateException("Role ROLE_USUARIO não encontrado!");
        });

        final UsuariosRolesID usuariosRolesID = new UsuariosRolesID(usuario.getId(), roleUsuario.getId());
        this.usuariosRolesRepository.save(new UsuariosRoles(usuariosRolesID));
    }

    @Override
    public UsuarioLoginOutDto autenticar(UsuarioLoginDto usuarioLoginDto) {
        this.authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(usuarioLoginDto.username(), usuarioLoginDto.senha())
        );

        UserDetails userDetails = this.usuarioDetailsService.loadUserByUsername(usuarioLoginDto.username());
        String token = this.jwtService.gerarToken(userDetails);
        return new UsuarioLoginOutDto(token);
    }
}
