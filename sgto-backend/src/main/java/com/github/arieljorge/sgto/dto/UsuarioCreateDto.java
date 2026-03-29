package com.github.arieljorge.sgto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCreateDto(
        @NotBlank @Size(max = 50) String username,
        @NotBlank @Size(max = 150) @Email String email,
        @NotBlank @Size(min = 6) String senha
) {}
