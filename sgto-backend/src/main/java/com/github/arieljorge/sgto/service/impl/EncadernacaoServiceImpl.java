package com.github.arieljorge.sgto.service.impl;

import com.github.arieljorge.sgto.dto.*;
import com.github.arieljorge.sgto.entity.Encadernacao;
import com.github.arieljorge.sgto.enumerator.PlataformaExterna;
import com.github.arieljorge.sgto.repository.EncadernacaoRepository;
import com.github.arieljorge.sgto.service.EncadernacaoService;
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
public class EncadernacaoServiceImpl implements EncadernacaoService {

    private final EncadernacaoRepository encadernacaoRepository;

    @Override
    @Transactional
    public void cadastrarEncadernacao(EncadernacaoCreateDto encadernacaoCreateDto) {
        if (this.encadernacaoRepository.existsByNomeAndPlataformaOrigem(encadernacaoCreateDto.nome(), encadernacaoCreateDto.plataformaOrigem())) {
            throw new RuntimeException("indentificada encadernação com o mesmo nome");
        }

        Encadernacao encadernacao = new Encadernacao();
        encadernacao.setNome(encadernacaoCreateDto.nome());
        encadernacao.setIdExterno(StringUtils.trimToNull(encadernacaoCreateDto.idExterno()));
        encadernacao.setPlataformaOrigem(encadernacaoCreateDto.plataformaOrigem());

        this.encadernacaoRepository.save(encadernacao);
    }

    @Override
    @Transactional
    public void atualizarEncadernacao(EncadernacaoUpdateDto encadernacaoUpdateDto) {
        Encadernacao encadernacao = this.encadernacaoRepository.findById(encadernacaoUpdateDto.id()).orElseThrow(() -> {
            return new RuntimeException("encadernação não encontrada para o id " + encadernacaoUpdateDto.id());
        });

        if (this.encadernacaoRepository.existsByNomeAndIdIsNot(encadernacao.getNome(), encadernacao.getId())) {
            throw new RuntimeException("indentificada encadernação com o mesmo nome");
        }

        encadernacao.setNome(encadernacaoUpdateDto.nome());
        encadernacao.setIdExterno(StringUtils.trimToNull(encadernacaoUpdateDto.idExterno()));

        this.encadernacaoRepository.save(encadernacao);
    }

    @Override
    public PageResponseDto<EncadernacaoOutDto> obterEncadernacoes(Pageable pageable, EncadernacaoFilterDto encadernacaoFilterDto) {
        final Page<Encadernacao> encadernacoes = this.encadernacaoRepository.findAllByFilter(
            pageable, Optional.ofNullable(encadernacaoFilterDto.plataformaOrigem()).map(PlataformaExterna::getValue).orElse(null)
        );

        return new PageResponseDto<>(encadernacoes, EncadernacaoOutDto::new);
    }

    @Override
    public EncadernacaoOutDto obterEncadernacaoPorId(Short idEncadernacao) {
        return this.encadernacaoRepository.findById(idEncadernacao).map(EncadernacaoOutDto::new).orElseThrow(() -> {
            return new RuntimeException("encadernação não encontrada para o id " + idEncadernacao);
        });
    }

    @Override
    @Transactional
    public void removerPorIds(List<Short> encadernacaoIds) {
        this.encadernacaoRepository.removeAllByIdIn(encadernacaoIds);
    }
}
