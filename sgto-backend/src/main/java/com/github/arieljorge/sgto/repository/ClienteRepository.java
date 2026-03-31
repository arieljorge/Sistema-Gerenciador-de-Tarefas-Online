package com.github.arieljorge.sgto.repository;

import com.github.arieljorge.sgto.entity.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Short> {

    boolean existsByNomeAndIdIsNot(
        @Size(max = 100) @NotBlank String nome,
        @NotNull Short idCliente
    );

    boolean existsByNome(@Size(max = 100) @NotBlank String nome);
}
