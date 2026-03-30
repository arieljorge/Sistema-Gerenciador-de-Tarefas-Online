package com.github.arieljorge.sgto.service;

import com.github.arieljorge.sgto.dto.ContribuicaoFilterDto;
import com.github.arieljorge.sgto.dto.ContribuicaoOutDto;
import com.github.arieljorge.sgto.dto.ContribuicaoUpsertDto;
import com.github.arieljorge.sgto.dto.PageResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContribuicaoService {
    void upsertContribuicoes(List<ContribuicaoUpsertDto> contribuicaoUpsertDtos);
    PageResponseDto<ContribuicaoOutDto> obterContribuicoes(Pageable pageable, ContribuicaoFilterDto contribuicaoFilterDto);
    ContribuicaoOutDto obterContribuicaoPorId(Short idContribuicao);
}
