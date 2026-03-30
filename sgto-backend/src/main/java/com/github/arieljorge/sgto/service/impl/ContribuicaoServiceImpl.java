package com.github.arieljorge.sgto.service.impl;

import com.github.arieljorge.sgto.dto.ContribuicaoFilterDto;
import com.github.arieljorge.sgto.dto.ContribuicaoOutDto;
import com.github.arieljorge.sgto.dto.ContribuicaoUpsertDto;
import com.github.arieljorge.sgto.dto.PageResponseDto;
import com.github.arieljorge.sgto.entity.Contribuicao;
import com.github.arieljorge.sgto.enumerator.PlataformaExterna;
import com.github.arieljorge.sgto.repository.ContribuicaoRepository;
import com.github.arieljorge.sgto.service.ContribuicaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContribuicaoServiceImpl implements ContribuicaoService {

    private final ContribuicaoRepository contribuicaoRepository;

    @Override
    public void upsertContribuicoes(List<ContribuicaoUpsertDto> contribuicaoUpsertDtos) {
        this.contribuicaoRepository.upsertContribuicoes(contribuicaoUpsertDtos);
    }

    @Override
    public PageResponseDto<ContribuicaoOutDto> obterContribuicoes(Pageable pageable, ContribuicaoFilterDto contribuicaoFilterDto) {
        final Page<Contribuicao> contribuicoes = this.contribuicaoRepository.findAllByFilter(
            pageable, Optional.ofNullable(contribuicaoFilterDto.plataformaOrigem()).map(PlataformaExterna::getValue).orElse(null)
        );

        return new PageResponseDto<>(contribuicoes, ContribuicaoOutDto::new);
    }

    @Override
    public ContribuicaoOutDto obterContribuicaoPorId(Short idContribuicao) {
        return this.contribuicaoRepository.findById(idContribuicao).map(ContribuicaoOutDto::new).orElseThrow(() -> {
            return new RuntimeException("contribuição não encontrada para o id " + idContribuicao);
        });
    }
}
