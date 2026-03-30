package com.github.arieljorge.sgto.repository;

import com.github.arieljorge.sgto.entity.Encadernacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EncadernacaoRepository extends JpaRepository<Encadernacao, Short>, EncadernacaoCustomRepository {

    @Query("""
        SELECT e FROM Encadernacao AS e
        WHERE 1=1
            AND (:plataforma_origem IS NULL OR e.plataformaOrigem = :plataforma_origem)
    """)
    Page<Encadernacao> findAllByFilter(
        Pageable pageable,
        @Param("plataforma_origem") String plataformaOrigem
    );
}
