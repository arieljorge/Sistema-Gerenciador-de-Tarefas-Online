package com.github.arieljorge.sgto.controller;

import com.github.arieljorge.sgto.dto.*;
import com.github.arieljorge.sgto.service.ContribuicaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contribuicao")
public class ContribuicaoController {

    private final ContribuicaoService contribuicaoService;

    @PostMapping
    public ResponseEntity<Void> upsertContribuicoes(@Valid @RequestBody List<ContribuicaoUpsertDto> contribuicaoUpsertDtos) {
        this.contribuicaoService.upsertContribuicoes(contribuicaoUpsertDtos);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponseDto<ContribuicaoOutDto>>> obterContribuicoes(Pageable pageable, ContribuicaoFilterDto contribuicaoFilterDto) {
        final PageResponseDto<ContribuicaoOutDto> response = this.contribuicaoService.obterContribuicoes(pageable, contribuicaoFilterDto);
        return ResponseEntity.ok(new ApiResponse<>(true, null, response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ContribuicaoOutDto>> obterContribuicao(@PathVariable("id") Short idContribuicao) {
        final ContribuicaoOutDto response = this.contribuicaoService.obterContribuicaoPorId(idContribuicao);
        return ResponseEntity.ok(new ApiResponse<>(true, null, response));
    }
}
