package com.github.arieljorge.sgto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record QuadroCreateDto(
        @NotBlank @Size(max = 120) String nome
) {
}
