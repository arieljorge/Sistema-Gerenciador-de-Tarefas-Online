package com.github.arieljorge.sgto.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioLoginDto(
    @NotBlank String username,
    @NotBlank String senha
) {}
