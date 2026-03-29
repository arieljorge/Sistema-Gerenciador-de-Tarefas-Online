package com.github.arieljorge.sgto.dto;

public record UsuarioLoginOutDto(
        String token,
        String tipo
) {
    public UsuarioLoginOutDto(String token) {
        this(token, "Bearer");
    }
}
