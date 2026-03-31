package com.github.arieljorge.sgto.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClienteEditDto(
        @NotNull Short id,
        Boolean ativo,
        @Size(max = 100) String nome,
        @Size(max = 100) String dbHost,
        @Size(max = 50) String dbUsername,
        @Size(max = 50) String dbPassword
) {}
