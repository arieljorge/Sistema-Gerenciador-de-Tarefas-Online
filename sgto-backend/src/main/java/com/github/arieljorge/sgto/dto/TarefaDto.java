package com.github.arieljorge.sgto.dto;

import com.github.arieljorge.sgto.entity.Tarefa;
import com.github.arieljorge.sgto.entity.Usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record TarefaDto(
        Integer id,
        Short idQuadro,
        String titulo,
        String descricao,
        LocalDateTime criadoEm,
        LocalDateTime prazo,
        List<UUID> usuarios
) {
    public TarefaDto(Tarefa tarefa, List<Usuario> usuarios) {
        this(
                tarefa.getId(),
                tarefa.getIdQuadro(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getCriadoEm(),
                tarefa.getPrazo(),
                usuarios.stream().map(Usuario::getId).toList()
        );
    }
}
