package com.github.arieljorge.sgto.service;

import com.github.arieljorge.sgto.dto.ClienteCreateDto;
import com.github.arieljorge.sgto.dto.ClienteEditDto;
import com.github.arieljorge.sgto.dto.ClienteOutDto;
import com.github.arieljorge.sgto.dto.PageResponseDto;
import org.springframework.data.domain.Pageable;

public interface ClienteService {
    ClienteOutDto cadastrarCliente(ClienteCreateDto clienteCreateDto);
    ClienteOutDto atualizarCliente(ClienteEditDto clienteEditDto);
    PageResponseDto<ClienteOutDto> obterClientes(Pageable pageable);
    ClienteOutDto obterCliente(Short idCliente);
    void removerCliente(Short idCliente);
}
