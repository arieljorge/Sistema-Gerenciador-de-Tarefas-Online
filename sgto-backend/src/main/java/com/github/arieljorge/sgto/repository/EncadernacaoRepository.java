package com.github.arieljorge.sgto.repository;

import com.github.arieljorge.sgto.entity.Encadernacao;
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

public interface EncadernacaoRepository extends JpaRepository<Encadernacao, Short> {

    @Query("""
        SELECT e FROM Encadernacao AS e
        WHERE 1=1
            AND (:plataforma_origem IS NULL OR e.plataformaOrigem = :plataforma_origem)
    """)
    Page<Encadernacao> findAllByFilter(
        Pageable pageable,
        @Param("plataforma_origem") String plataformaOrigem
    );

    @Transactional
    void removeAllByIdIn(List<Short> encadernacaoIds);

    boolean existsByNomeAndPlataformaOrigem(@NotBlank @Size(max = 100) String nome, @NotNull PlataformaExterna plataformaExterna);

    boolean existsByNomeAndIdIsNot(@Size(max = 100) String nome, Short id);
}
