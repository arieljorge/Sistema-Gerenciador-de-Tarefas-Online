package com.github.arieljorge.sgto.dto;

import com.github.arieljorge.sgto.entity.Contribuicao;
import com.github.arieljorge.sgto.enumerator.PlataformaExterna;

public record ContribuicaoOutDto(
        Short id,
        String nome,
        String idExterno,
        PlataformaExterna plataformaOrigem
) {
    public ContribuicaoOutDto(Contribuicao contribuicao) {
        this(
            contribuicao.getId(),
            contribuicao.getNome(),
            contribuicao.getIdExterno(),
            contribuicao.getPlataformaOrigem()
        );
    }
}
