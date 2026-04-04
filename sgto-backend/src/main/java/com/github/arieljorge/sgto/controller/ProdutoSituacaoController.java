package com.github.arieljorge.sgto.controller;

import com.github.arieljorge.sgto.dto.*;
import com.github.arieljorge.sgto.service.ProdutoSituacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produto-situacao")
public class ProdutoSituacaoController {

    private final ProdutoSituacaoService produtoSituacaoService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> upsertProdutoSituacoes(@Valid @RequestBody ProdutoSituacaoCreateDto produtoSituacaoCreateDto) {
        this.produtoSituacaoService.cadastrarProdutoSituacao(produtoSituacaoCreateDto);
        return ResponseEntity.ok(new ApiResponse<>(true, "produto situações salvas com sucesso.", null));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Void>> editarEncadernacao(@Valid @RequestBody ProdutoSituacaoUpdateDto produtoSituacaoUpdateDto) {
        this.produtoSituacaoService.atualizarProdutoSituacao(produtoSituacaoUpdateDto);
        return ResponseEntity.ok(new ApiResponse<>(true, "encadernações salva com sucesso.", null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponseDto<ProdutoSituacaoOutDto>>> obterProdutoSituacoes(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
            ProdutoSituacaoFilterDto produtoSituacaoFilterDto
    ) {
        final PageResponseDto<ProdutoSituacaoOutDto> response = this.produtoSituacaoService.obterProdutoSituacoes(pageable, produtoSituacaoFilterDto);
        return ResponseEntity.ok(new ApiResponse<>(true, null, response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProdutoSituacaoOutDto>> obterProdutoSituacaoId(@PathVariable("id") Short idProdutoSituacao) {
        final ProdutoSituacaoOutDto response = this.produtoSituacaoService.obterProdutoSituacaoPorId(idProdutoSituacao);
        return ResponseEntity.ok(new ApiResponse<>(true, null, response));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deletarProdutoSituacoes(@Valid @RequestBody List<Short> produtoSituacoesIds) {
        this.produtoSituacaoService.removerPorIds(produtoSituacoesIds);
        return ResponseEntity.ok(new ApiResponse<>(true, "produto situações deletadas com sucesso.", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletarProdutoSituacao(@PathVariable("id") Short idProdutoSituacao) {
        this.produtoSituacaoService.removerPorIds(List.of(idProdutoSituacao));
        return ResponseEntity.ok(new ApiResponse<>(true, "produto situação deletada com sucesso.", null));
    }
}
