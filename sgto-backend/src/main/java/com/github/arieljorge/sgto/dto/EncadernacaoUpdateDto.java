package com.github.arieljorge.sgto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EncadernacaoUpdateDto(
        @NotNull Short id,
        @NotBlank @Size(max = 100) String nome,
        @Size(max = 50) String idExterno
) {
}
