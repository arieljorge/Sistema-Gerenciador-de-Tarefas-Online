package com.github.arieljorge.sgto.controller;

import com.github.arieljorge.sgto.dto.*;
import com.github.arieljorge.sgto.service.TarefaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tarefa")
public class TarefaController {

    private final TarefaService tarefaService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TarefaSimpleDto>>> obterTarefas(@RequestParam(name = "idQuadro") Short idQuadro) {
        List<TarefaSimpleDto> tarefaSimpleDtos = this.tarefaService.obterTarefas(idQuadro);
        return ResponseEntity.ok(new ApiResponse<>(true, null, tarefaSimpleDtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TarefaDto>> obterTarefa(@PathVariable Integer id) {
        TarefaDto tarefa = this.tarefaService.obterTarefa(id);
        return ResponseEntity.ok(new ApiResponse<>(true, null, tarefa));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TarefaDto>> cadastrarTarefa(@Valid @RequestBody TarefaCreateDto tarefaCreateDto) {
        TarefaDto tarefa = this.tarefaService.cadastrarTarefa(tarefaCreateDto);
        return ResponseEntity.ok(new ApiResponse<>(true, null, tarefa));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<TarefaDto>> atualizarTarefa(@Valid @RequestBody TarefaEditDto tarefaEditDto) {
        TarefaDto tarefa = this.tarefaService.atualizarTarefa(tarefaEditDto);
        return ResponseEntity.ok(new ApiResponse<>(true, null, tarefa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> removerTarefa(@PathVariable Integer id) {
        this.tarefaService.removerTarefa(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "tarefa removida com sucesso", null));
    }
}
