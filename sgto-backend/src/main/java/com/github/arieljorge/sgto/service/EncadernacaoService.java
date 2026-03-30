package com.github.arieljorge.sgto.service;

import com.github.arieljorge.sgto.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EncadernacaoService {
    void upsertEncadernacoes(List<EncadernacaoUpsertDto> encadernacaoUpsertDtos);
    PageResponseDto<EncadernacaoOutDto> obterEncadernacoes(Pageable pageable, EncadernacaoFilterDto encadernacaoFilterDto);
    EncadernacaoOutDto obterEncadernacaoPorId(Short idEncadernacao);
}
