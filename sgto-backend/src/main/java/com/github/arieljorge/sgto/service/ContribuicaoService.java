package com.github.arieljorge.sgto.service;

import com.github.arieljorge.sgto.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContribuicaoService {
    void cadastrarContribuicao(ContribuicaoCreateDto contribuicaoCreateDto);
    void atualizarContribuicao(ContribuicaoUpdateDto contribuicaoUpdateDto);
    PageResponseDto<ContribuicaoOutDto> obterContribuicoes(Pageable pageable, ContribuicaoFilterDto contribuicaoFilterDto);
    ContribuicaoOutDto obterContribuicaoPorId(Short idContribuicao);
    void removerPorIds(List<Short> contribuicaoIds);
}
