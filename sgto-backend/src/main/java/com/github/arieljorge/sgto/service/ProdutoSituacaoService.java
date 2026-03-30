package com.github.arieljorge.sgto.service;

import com.github.arieljorge.sgto.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProdutoSituacaoService {
    void upsertProdutoSituacoes(List<ProdutoSituacaoUpsertDto> produtoSituacaoUpsertDtos);
    PageResponseDto<ProdutoSituacaoOutDto> obterProdutoSituacoes(Pageable pageable, ProdutoSituacaoFilterDto produtoSituacaoFilterDto);
    ProdutoSituacaoOutDto obterProdutoSituacaoPorId(Short idProdutoSituacao);
    void removerPorIds(List<Short> produtoSituacaoIds);
}
