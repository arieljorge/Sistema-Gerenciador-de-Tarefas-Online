package com.github.arieljorge.sgto.service;

import com.github.arieljorge.sgto.dto.TarefaDto;
import com.github.arieljorge.sgto.dto.TarefaSimpleDto;
import com.github.arieljorge.sgto.dto.TarefaCreateDto;
import com.github.arieljorge.sgto.dto.TarefaEditDto;

import java.util.List;

public interface TarefaService {
    List<TarefaSimpleDto> obterTarefas(Short idQuadro);
    TarefaDto obterTarefa(Integer idTarefa);
    TarefaDto cadastrarTarefa(TarefaCreateDto tarefaCreateDto);
    TarefaDto atualizarTarefa(TarefaEditDto tarefaEditDto);
    void removerTarefa(Integer idTarefa);
}
