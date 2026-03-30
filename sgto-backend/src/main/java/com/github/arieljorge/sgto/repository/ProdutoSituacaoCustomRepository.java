package com.github.arieljorge.sgto.repository;

import com.github.arieljorge.sgto.dto.ProdutoSituacaoUpsertDto;

import java.util.List;

public interface ProdutoSituacaoCustomRepository {
    void upsertProdutoSituacoes(List<ProdutoSituacaoUpsertDto> produtoSituacaoUpsertDtos);
}
