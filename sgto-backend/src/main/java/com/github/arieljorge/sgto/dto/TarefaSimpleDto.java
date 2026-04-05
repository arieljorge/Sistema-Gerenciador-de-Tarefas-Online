package com.github.arieljorge.sgto.dto;

import com.github.arieljorge.sgto.entity.Tarefa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public record TarefaSimpleDto(
    Integer id,
    String titulo,
    LocalDate criadoEm,
    LocalDate prazo
) {
    public TarefaSimpleDto(Tarefa tarefa) {
        this(
                tarefa.getId(),
                tarefa.getTitulo(),
                Optional.ofNullable(tarefa.getCriadoEm()).map(LocalDateTime::toLocalDate).orElse(null),
                Optional.ofNullable(tarefa.getPrazo()).map(LocalDateTime::toLocalDate).orElse(null)
        );
    }
}
