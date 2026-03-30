package com.github.arieljorge.sgto.dto;

import com.github.arieljorge.sgto.enumerator.PlataformaExterna;
import jakarta.annotation.Nullable;
import org.springframework.web.bind.annotation.RequestParam;

public record ContribuicaoFilterDto(
        @RequestParam(name = "plataforma", required = false) @Nullable PlataformaExterna plataformaOrigem
) {}
