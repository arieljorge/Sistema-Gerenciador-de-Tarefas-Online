package com.github.arieljorge.sgto.repository.impl;

import com.github.arieljorge.sgto.dto.ProdutoSituacaoUpsertDto;
import com.github.arieljorge.sgto.enumerator.PlataformaExterna;
import com.github.arieljorge.sgto.repository.ProdutoSituacaoCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProdutoSituacaoCustomRepositoryImpl implements ProdutoSituacaoCustomRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void upsertProdutoSituacoes(List<ProdutoSituacaoUpsertDto> produtoSituacaoUpsertDtos) {
        final String querySQL = """
            INSERT INTO produto_situacao (nome, id_externo, plataforma_origem)
            VALUES (?, ?, ?)
            ON CONFLICT (nome, plataforma_origem)
            DO UPDATE SET
                nome = EXCLUDED.nome,
                id_externo = EXCLUDED.id_externo;
        """;

        jdbcTemplate.batchUpdate(querySQL, produtoSituacaoUpsertDtos, 50, (ps, item) -> {
            ps.setObject(1, item.nome());
            ps.setObject(2, item.idExterno());
            ps.setObject(3, Optional.ofNullable(item.plataformaOrigem()).map(PlataformaExterna::getValue).orElse(null));
        });
    }
}
