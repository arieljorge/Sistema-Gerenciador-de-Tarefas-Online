package com.github.arieljorge.sgto.repository;

import com.github.arieljorge.sgto.entity.Contribuicao;
import com.github.arieljorge.sgto.enumerator.PlataformaExterna;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContribuicaoRepository extends JpaRepository<Contribuicao, Short> {

    @Query("""
        SELECT c FROM Contribuicao AS c
        WHERE 1=1
            AND (:plataforma_origem IS NULL OR c.plataformaOrigem = :plataforma_origem)
    """)
    Page<Contribuicao> findAllByFilter(
        Pageable pageable,
        @Param("plataforma_origem") String plataformaOrigem
    );

    @Transactional
    void deleteAllByIdIn(List<Short> contribuicaoIds);

    boolean existsByNomeAndPlataformaOrigem(@NotBlank @Size(max = 100) String nome, @NotNull PlataformaExterna plataformaExterna);

    boolean existsByNomeAndIdIsNot(@Size(max = 100) String nome, Short id);
}
