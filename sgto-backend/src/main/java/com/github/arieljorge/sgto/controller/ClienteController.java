package com.github.arieljorge.sgto.controller;

import com.github.arieljorge.sgto.dto.*;
import com.github.arieljorge.sgto.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ApiResponse<ClienteOutDto>> cadastrarCliente(@Valid @RequestBody ClienteCreateDto clienteCreateDto) {
        final ClienteOutDto response = this.clienteService.cadastrarCliente(clienteCreateDto);
        return ResponseEntity.ok(new ApiResponse<>(true, null, response));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<ClienteOutDto>> atualizarCliente(@Valid @RequestBody ClienteEditDto clienteEditDto) {
        final ClienteOutDto response = this.clienteService.atualizarCliente(clienteEditDto);
        return ResponseEntity.ok(new ApiResponse<>(true, null, response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponseDto<ClienteOutDto>>> obterClientes(Pageable pageable) {
        final PageResponseDto<ClienteOutDto> response = this.clienteService.obterClientes(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, null, response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteOutDto>> obterClientePorId(@PathVariable("id") Short idCliente) {
        final ClienteOutDto response = this.clienteService.obterCliente(idCliente);
        return ResponseEntity.ok(new ApiResponse<>(true, null, response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> removerCliente(@PathVariable("id") Short idCliente) {
        this.clienteService.removerCliente(idCliente);
        return ResponseEntity.ok(new ApiResponse<>(true, "cliente removido com sucesso", null));
    }
}
