package com.github.arieljorge.sgto.service.impl;

import com.github.arieljorge.sgto.dto.*;
import com.github.arieljorge.sgto.entity.Contribuicao;
import com.github.arieljorge.sgto.entity.Encadernacao;
import com.github.arieljorge.sgto.enumerator.PlataformaExterna;
import com.github.arieljorge.sgto.repository.ContribuicaoRepository;
import com.github.arieljorge.sgto.service.ContribuicaoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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
    @Transactional
    public void cadastrarContribuicao(ContribuicaoCreateDto contribuicaoCreateDto) {
        if (this.contribuicaoRepository.existsByNomeAndPlataformaOrigem(contribuicaoCreateDto.nome(), contribuicaoCreateDto.plataformaOrigem())) {
            throw new RuntimeException("indentificada contribuição com o mesmo nome");
        }

        Contribuicao contribuicao = new Contribuicao();
        contribuicao.setNome(contribuicaoCreateDto.nome());
        contribuicao.setIdExterno(StringUtils.trimToNull(contribuicaoCreateDto.idExterno()));
        contribuicao.setPlataformaOrigem(contribuicaoCreateDto.plataformaOrigem());

        this.contribuicaoRepository.save(contribuicao);
    }

    @Override
    @Transactional
    public void atualizarContribuicao(ContribuicaoUpdateDto contribuicaoUpdateDto) {
        Contribuicao contribuicao = this.contribuicaoRepository.findById(contribuicaoUpdateDto.id()).orElseThrow(() -> {
            return new RuntimeException("contribuição não encontrada para o id " + contribuicaoUpdateDto.id());
        });

        if (this.contribuicaoRepository.existsByNomeAndIdIsNot(contribuicao.getNome(), contribuicao.getId())) {
            throw new RuntimeException("indentificada contribuição com o mesmo nome");
        }

        contribuicao.setNome(contribuicaoUpdateDto.nome());
        contribuicao.setIdExterno(StringUtils.trimToNull(contribuicaoUpdateDto.idExterno()));

        this.contribuicaoRepository.save(contribuicao);
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

    @Override
    @Transactional
    public void removerPorIds(List<Short> contribuicaoIds) {
        this.contribuicaoRepository.deleteAllByIdIn(contribuicaoIds);
    }
}
