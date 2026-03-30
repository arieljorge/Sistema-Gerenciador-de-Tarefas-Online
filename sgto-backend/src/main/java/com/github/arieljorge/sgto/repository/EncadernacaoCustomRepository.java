package com.github.arieljorge.sgto.repository;

import com.github.arieljorge.sgto.dto.EncadernacaoUpsertDto;

import java.util.List;

public interface EncadernacaoCustomRepository {
    void upsertEncadernacoes(List<EncadernacaoUpsertDto> encadernacaoUpsertDtos);
}
