package com.github.arieljorge.sgto.repository;

import com.github.arieljorge.sgto.entity.Contribuicao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContribuicaoRepository extends JpaRepository<Contribuicao, Short>, ContribuicaoCustomRepository {

    @Query("""
        SELECT c FROM Contribuicao AS c
        WHERE 1=1
            AND (:plataforma_origem IS NULL OR c.plataformaOrigem = :plataforma_origem)
    """)
    Page<Contribuicao> findAllByFilter(
        Pageable pageable,
        @Param("plataforma_origem") String plataformaOrigem
    );
}
