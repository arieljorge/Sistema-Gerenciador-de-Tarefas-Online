package com.github.arieljorge.sgto.controller;

import com.github.arieljorge.sgto.dto.ApiResponse;
import com.github.arieljorge.sgto.dto.QuadroCreateDto;
import com.github.arieljorge.sgto.entity.Quadro;
import com.github.arieljorge.sgto.service.QuadroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quadro")
public class QuadroController {

    private final QuadroService quadroService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Quadro>>> obterQuadros() {
        List<Quadro> quadros = quadroService.obterQuadros();
        return ResponseEntity.ok(new ApiResponse<>(true, null, quadros));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Quadro>> cadastrarQuadro(@RequestBody QuadroCreateDto quadroCreateDto) {
        Quadro quadro = quadroService.cadastrarQuadro(quadroCreateDto.nome());
        return ResponseEntity.ok(new ApiResponse<>(true, null, quadro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletarQuadro(@PathVariable Short id) {
        quadroService.removerQuadro(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "quadro removido com sucesso", null));
    }
}
