package com.github.arieljorge.sgto.repository.impl;

import com.github.arieljorge.sgto.dto.EncadernacaoUpsertDto;
import com.github.arieljorge.sgto.enumerator.PlataformaExterna;
import com.github.arieljorge.sgto.repository.EncadernacaoCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EncadernacaoCustomRepositoryImpl implements EncadernacaoCustomRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void upsertEncadernacoes(List<EncadernacaoUpsertDto> encadernacaoUpsertDtos) {
        final String querySQL = """
            INSERT INTO encadernacao (nome, id_externo, plataforma_origem)
            VALUES (?, ?, ?)
            ON CONFLICT (nome, plataforma_origem)
            DO UPDATE SET
                id_externo = EXCLUDED.id_externo;
        """;

        jdbcTemplate.batchUpdate(querySQL, encadernacaoUpsertDtos, 50, (ps, item) -> {
            ps.setObject(1, item.nome());
            ps.setObject(2, item.idExterno());
            ps.setObject(3, Optional.ofNullable(item.plataformaOrigem()).map(PlataformaExterna::getValue).orElse(null));
        });
    }
}
