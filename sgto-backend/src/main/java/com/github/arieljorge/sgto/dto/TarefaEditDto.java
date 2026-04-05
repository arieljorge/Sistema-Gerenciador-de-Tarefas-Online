package com.github.arieljorge.sgto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record TarefaEditDto(
        @NotNull Integer id,
        @NotNull Short idQuadro,
        @NotBlank String titulo,
        String descricao,
        LocalDate prazo,
        List<UUID> usuarios
) {
}
