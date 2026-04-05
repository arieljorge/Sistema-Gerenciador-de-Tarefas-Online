package com.github.arieljorge.sgto.controller;

import com.github.arieljorge.sgto.dto.ApiResponse;
import com.github.arieljorge.sgto.dto.UsuarioCreateDto;
import com.github.arieljorge.sgto.dto.UsuarioSimpleDto;
import com.github.arieljorge.sgto.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UsuarioSimpleDto>>> obterUsuarios() {
        List<UsuarioSimpleDto> usuarios = this.usuarioService.obterUsuarios();
        return ResponseEntity.ok(new ApiResponse<>(true, null, usuarios));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> cadastrarUsuario(@Valid @RequestBody UsuarioCreateDto usuarioCreateDto) {
        this.usuarioService.cadastrarUsuario(usuarioCreateDto);
        return ResponseEntity.ok(new ApiResponse<>(true, "usuario cadastrado com sucesso", null));
    }
}
