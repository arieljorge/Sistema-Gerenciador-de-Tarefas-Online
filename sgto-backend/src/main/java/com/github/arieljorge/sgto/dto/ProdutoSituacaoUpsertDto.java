package com.github.arieljorge.sgto.dto;

import com.github.arieljorge.sgto.enumerator.PlataformaExterna;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProdutoSituacaoUpsertDto(
        @NotBlank @Size(max = 100) String nome,
        @Size(max = 50) String idExterno,
        @NotNull PlataformaExterna plataformaOrigem
) {
}
