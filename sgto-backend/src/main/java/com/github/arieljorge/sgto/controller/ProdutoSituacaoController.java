package com.github.arieljorge.sgto.controller;

import com.github.arieljorge.sgto.dto.*;
import com.github.arieljorge.sgto.service.ProdutoSituacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produto-situacao")
public class ProdutoSituacaoController {

    private final ProdutoSituacaoService produtoSituacaoService;

    @PostMapping
    public ResponseEntity<Void> upsertProdutoSituacoes(@Valid @RequestBody List<ProdutoSituacaoUpsertDto> produtoSituacaoUpsertDtos) {
        this.produtoSituacaoService.upsertProdutoSituacoes(produtoSituacaoUpsertDtos);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponseDto<ProdutoSituacaoOutDto>>> obterProdutoSituacoes(Pageable pageable, ProdutoSituacaoFilterDto produtoSituacaoFilterDto) {
        final PageResponseDto<ProdutoSituacaoOutDto> response = this.produtoSituacaoService.obterProdutoSituacoes(pageable, produtoSituacaoFilterDto);
        return ResponseEntity.ok(new ApiResponse<>(true, null, response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProdutoSituacaoOutDto>> obterProdutoSituacaoId(@PathVariable("id") Short idProdutoSituacao) {
        final ProdutoSituacaoOutDto response = this.produtoSituacaoService.obterProdutoSituacaoPorId(idProdutoSituacao);
        return ResponseEntity.ok(new ApiResponse<>(true, null, response));
    }
}
