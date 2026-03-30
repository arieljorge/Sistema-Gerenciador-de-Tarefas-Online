package com.github.arieljorge.sgto.dto;

import com.github.arieljorge.sgto.entity.Cliente;
import jakarta.validation.constraints.Size;

public record ClienteOutDto(
        Short id,
        Boolean ativo,
        @Size(max = 100) String nome,
        @Size(max = 100) String dbHost,
        @Size(max = 50) String dbUsername,
        @Size(max = 50) String dbPassword
) {
    public ClienteOutDto(Cliente cliente) {
        this(
                cliente.getId(),
                cliente.getAtivo(),
                cliente.getNome(),
                cliente.getDbHost(),
                cliente.getDbUsername(),
                cliente.getDbPassword()
        );
    }
}
