package com.github.arieljorge.sgto.service.impl;

import com.github.arieljorge.sgto.dto.*;
import com.github.arieljorge.sgto.entity.Encadernacao;
import com.github.arieljorge.sgto.entity.ProdutoSituacao;
import com.github.arieljorge.sgto.enumerator.PlataformaExterna;
import com.github.arieljorge.sgto.repository.ProdutoSituacaoRepository;
import com.github.arieljorge.sgto.service.ProdutoSituacaoService;
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
public class ProdutoSituacaoServiceImpl implements ProdutoSituacaoService {

    private final ProdutoSituacaoRepository produtoSituacaoRepository;

    @Override
    @Transactional
    public void cadastrarProdutoSituacao(ProdutoSituacaoCreateDto produtoSituacaoCreateDto) {
        if (this.produtoSituacaoRepository.existsByNomeAndPlataformaOrigem(produtoSituacaoCreateDto.nome(), produtoSituacaoCreateDto.plataformaOrigem())) {
            throw new RuntimeException("indentificada produto situação com o mesmo nome");
        }

        ProdutoSituacao produtoSituacao = new ProdutoSituacao();
        produtoSituacao.setNome(produtoSituacaoCreateDto.nome());
        produtoSituacao.setIdExterno(StringUtils.trimToNull(produtoSituacaoCreateDto.idExterno()));
        produtoSituacao.setPlataformaOrigem(produtoSituacaoCreateDto.plataformaOrigem());

        this.produtoSituacaoRepository.save(produtoSituacao);
    }

    @Override
    @Transactional
    public void atualizarProdutoSituacao(ProdutoSituacaoUpdateDto produtoSituacaoUpdateDto) {
        ProdutoSituacao produtoSituacao = this.produtoSituacaoRepository.findById(produtoSituacaoUpdateDto.id()).orElseThrow(() -> {
            return new RuntimeException("produto situação não encontrada para o id " + produtoSituacaoUpdateDto.id());
        });

        if (this.produtoSituacaoRepository.existsByNomeAndIdIsNot(produtoSituacao.getNome(), produtoSituacao.getId())) {
            throw new RuntimeException("indentificada produto situação com o mesmo nome");
        }

        produtoSituacao.setNome(produtoSituacaoUpdateDto.nome());
        produtoSituacao.setIdExterno(StringUtils.trimToNull(produtoSituacaoUpdateDto.idExterno()));

        this.produtoSituacaoRepository.save(produtoSituacao);
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

    @Override
    @Transactional
    public void removerPorIds(List<Short> produtoSituacaoIds) {
        this.produtoSituacaoRepository.removeAllByIdIn(produtoSituacaoIds);
    }
}
