package com.github.arieljorge.sgto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteEditDto(
        Short id,
        Boolean ativo,
        @Size(max = 100) @NotBlank String nome,
        @Size(max = 100) String dbHost,
        @Size(max = 50) String dbUsername,
        @Size(max = 50) String dbPassword
) {}
