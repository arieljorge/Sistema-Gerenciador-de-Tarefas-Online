package com.github.arieljorge.sgto.dto;

import com.github.arieljorge.sgto.entity.ProdutoSituacao;
import com.github.arieljorge.sgto.enumerator.PlataformaExterna;

public record ProdutoSituacaoOutDto(
        Short id,
        String nome,
        String idExterno,
        PlataformaExterna plataformaOrigem
) {
    public ProdutoSituacaoOutDto(ProdutoSituacao produtoSituacao) {
        this(
            produtoSituacao.getId(),
            produtoSituacao.getNome(),
            produtoSituacao.getIdExterno(),
            produtoSituacao.getPlataformaOrigem()
        );
    }
}
