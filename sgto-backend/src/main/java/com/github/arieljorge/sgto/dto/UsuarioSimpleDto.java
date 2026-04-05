package com.github.arieljorge.sgto.dto;

import com.github.arieljorge.sgto.entity.Usuario;

import java.util.UUID;

public record UsuarioSimpleDto(
        UUID id,
        String nome
) {
    public UsuarioSimpleDto(Usuario usuario) {
        this(usuario.getId(), usuario.getUsername());
    }
}
