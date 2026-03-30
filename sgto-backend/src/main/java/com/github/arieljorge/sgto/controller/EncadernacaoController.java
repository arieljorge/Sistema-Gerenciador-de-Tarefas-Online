package com.github.arieljorge.sgto.controller;

import com.github.arieljorge.sgto.dto.*;
import com.github.arieljorge.sgto.service.EncadernacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/encadernacao")
public class EncadernacaoController {

    private final EncadernacaoService encadernacaoService;

    @PostMapping
    public ResponseEntity<Void> upsertEncadernacoes(@Valid @RequestBody List<EncadernacaoUpsertDto> encadernacaoUpsertDtos) {
        this.encadernacaoService.upsertEncadernacoes(encadernacaoUpsertDtos);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponseDto<EncadernacaoOutDto>>> obterEncadernacoes(Pageable pageable, EncadernacaoFilterDto encadernacaoFilterDto) {
        final PageResponseDto<EncadernacaoOutDto> response = this.encadernacaoService.obterEncadernacoes(pageable, encadernacaoFilterDto);
        return ResponseEntity.ok(new ApiResponse<>(true, null, response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EncadernacaoOutDto>> obterEncadernacao(@PathVariable("id") Short idEncadernacao) {
        final EncadernacaoOutDto response = this.encadernacaoService.obterEncadernacaoPorId(idEncadernacao);
        return ResponseEntity.ok(new ApiResponse<>(true, null, response));
    }
}
