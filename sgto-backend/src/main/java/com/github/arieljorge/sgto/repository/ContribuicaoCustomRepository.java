package com.github.arieljorge.sgto.repository;

import com.github.arieljorge.sgto.dto.ContribuicaoUpsertDto;

import java.util.List;

public interface ContribuicaoCustomRepository {
    void upsertContribuicoes(List<ContribuicaoUpsertDto> contribuicaoUpsertDtos);
}
