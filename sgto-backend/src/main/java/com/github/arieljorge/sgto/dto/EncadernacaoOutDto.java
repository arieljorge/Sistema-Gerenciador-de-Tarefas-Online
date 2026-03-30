package com.github.arieljorge.sgto.dto;

import com.github.arieljorge.sgto.entity.Encadernacao;
import com.github.arieljorge.sgto.enumerator.PlataformaExterna;

public record EncadernacaoOutDto(
        Short id,
        String nome,
        String idExterno,
        PlataformaExterna plataformaOrigem
) {
    public EncadernacaoOutDto(Encadernacao encadernacao) {
        this(
            encadernacao.getId(),
            encadernacao.getNome(),
            encadernacao.getIdExterno(),
            encadernacao.getPlataformaOrigem()
        );
    }
}
