package com.github.arieljorge.sgto.service.impl;

import com.github.arieljorge.sgto.entity.Usuario;
import com.github.arieljorge.sgto.repository.UsuarioRepository;
import com.github.arieljorge.sgto.repository.UsuariosRolesRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final UsuariosRolesRepository usuariosRolesRepository;

    @Override
    public UserDetails loadUserByUsername(final @NonNull String username) throws UsernameNotFoundException {
        final Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(() -> {
            return new UsernameNotFoundException("Usuário não encontrado. " + username);
        });

        final List<SimpleGrantedAuthority> authorities;
        authorities = usuariosRolesRepository
                .findNomeRolesByIdUsuario(usuario.getId())
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

        return User
                .builder()
                .username(usuario.getUsername())
                .password(usuario.getSenhaHash())
                .authorities(authorities)
                .accountLocked(!Boolean.TRUE.equals(usuario.getAtivo()))
                .build();
    }
}
