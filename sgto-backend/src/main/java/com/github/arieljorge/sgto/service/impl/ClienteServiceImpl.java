package com.github.arieljorge.sgto.service.impl;

import com.github.arieljorge.sgto.dto.ClienteCreateDto;
import com.github.arieljorge.sgto.dto.ClienteEditDto;
import com.github.arieljorge.sgto.dto.ClienteOutDto;
import com.github.arieljorge.sgto.dto.PageResponseDto;
import com.github.arieljorge.sgto.entity.Cliente;
import com.github.arieljorge.sgto.repository.ClienteRepository;
import com.github.arieljorge.sgto.service.ClienteService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    @Transactional
    public ClienteOutDto cadastrarCliente(ClienteCreateDto clienteCreateDto) {
        if (this.clienteRepository.existsByNome(clienteCreateDto.nome())) {
            throw new RuntimeException("erro ao cadastrar cliente, cliente com mesmo nome já cadastrado.");
        }

        Cliente cliente = new Cliente();
        cliente.setAtivo(true);
        cliente.setNome(clienteCreateDto.nome());
        cliente.setDbHost(clienteCreateDto.dbHost());
        cliente.setDbUsername(clienteCreateDto.dbUsername());
        cliente.setDbPassword(clienteCreateDto.dbPassword());

        cliente = this.clienteRepository.save(cliente);

        return new ClienteOutDto(cliente);
    }

    @Override
    @Transactional
    public ClienteOutDto atualizarCliente(ClienteEditDto clienteEditDto) {
        Cliente cliente = this.clienteRepository.findById(clienteEditDto.id()).orElseThrow(() -> {
            return new RuntimeException("erro ao atualizar cliente, cliente não encontrado para o id " + clienteEditDto.id());
        });

        if (this.clienteRepository.existsByNomeAndIdIsNot(clienteEditDto.nome(), clienteEditDto.id())) {
            throw new RuntimeException("erro ao atualizar cliente, cliente com mesmo nome já cadastrado.");
        }

        Optional.ofNullable(clienteEditDto.ativo()).ifPresent(cliente::setAtivo);
        Optional.ofNullable(clienteEditDto.nome()).ifPresent(cliente::setNome);
        Optional.ofNullable(clienteEditDto.dbHost()).ifPresent(cliente::setDbHost);
        Optional.ofNullable(clienteEditDto.dbUsername()).ifPresent(cliente::setDbUsername);
        Optional.ofNullable(clienteEditDto.dbPassword()).ifPresent(cliente::setDbPassword);

        cliente = this.clienteRepository.save(cliente);

        return new ClienteOutDto(cliente);
    }

    @Override
    public PageResponseDto<ClienteOutDto> obterClientes(Pageable pageable) {
        final Page<Cliente> clientes = this.clienteRepository.findAll(pageable);
        return new PageResponseDto<>(clientes, ClienteOutDto::new);
    }

    @Override
    public ClienteOutDto obterCliente(@NotNull(message = "idCliente não pode ser nulo.") Short idCliente) {
        final Cliente cliente = this.clienteRepository.findById(idCliente).orElseThrow(() -> {
            return new RuntimeException("erro ao obter cliente, cliente não encontrado para o id " + idCliente);
        });

        return new ClienteOutDto(cliente);
    }

    @Override
    @Transactional
    public void removerCliente(@NotNull(message = "idCliente não pode ser nulo.") Short idCliente) {
        this.clienteRepository.deleteById(idCliente);
    }
}
