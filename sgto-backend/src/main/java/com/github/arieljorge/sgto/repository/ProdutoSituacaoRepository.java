package com.github.arieljorge.sgto.repository;

import com.github.arieljorge.sgto.entity.ProdutoSituacao;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoSituacaoRepository extends JpaRepository<ProdutoSituacao, Short>, ProdutoSituacaoCustomRepository {

    @Query("""
        SELECT ps FROM ProdutoSituacao AS ps
        WHERE 1=1
            AND (:plataforma_origem IS NULL OR ps.plataformaOrigem = :plataforma_origem)
    """)
    Page<ProdutoSituacao> findAllByFilter(
        Pageable pageable,
        @Param("plataforma_origem") String plataformaOrigem
    );

    @Transactional
    void removeAllByIdIn(List<Short> produtoSituacaoIds);
}
