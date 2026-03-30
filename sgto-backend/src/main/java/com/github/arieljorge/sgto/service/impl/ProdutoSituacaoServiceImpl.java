package com.github.arieljorge.sgto.service.impl;

import com.github.arieljorge.sgto.dto.*;
import com.github.arieljorge.sgto.entity.ProdutoSituacao;
import com.github.arieljorge.sgto.enumerator.PlataformaExterna;
import com.github.arieljorge.sgto.repository.ProdutoSituacaoRepository;
import com.github.arieljorge.sgto.service.ProdutoSituacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoSituacaoServiceImpl implements ProdutoSituacaoService {

    private final ProdutoSituacaoRepository produtoSituacaoRepository;

    @Override
    public void upsertProdutoSituacoes(List<ProdutoSituacaoUpsertDto> produtoSituacoesUpsertDtos) {
        this.produtoSituacaoRepository.upsertProdutoSituacoes(produtoSituacoesUpsertDtos);
    }

    @Override
    public PageResponseDto<ProdutoSituacaoOutDto> obterProdutoSituacoes(Pageable pageable, ProdutoSituacaoFilterDto produtoSituacaoFilterDto) {
        final Page<ProdutoSituacao> produtoSituacoes = this.produtoSituacaoRepository.findAllByFilter(
            pageable, Optional.ofNullable(produtoSituacaoFilterDto.plataformaOrigem()).map(PlataformaExterna::getValue).orElse(null)
        );

        return new PageResponseDto<>(produtoSituacoes, ProdutoSituacaoOutDto::new);
    }

    @Override
    public ProdutoSituacaoOutDto obterProdutoSituacaoPorId(Short idProdutoSituacao) {
        return this.produtoSituacaoRepository.findById(idProdutoSituacao).map(ProdutoSituacaoOutDto::new).orElseThrow(() -> {
            return new RuntimeException("produto situação não encontrado para o id " + idProdutoSituacao);
        });
    }
}
