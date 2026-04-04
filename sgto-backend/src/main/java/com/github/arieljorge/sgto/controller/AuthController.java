package com.github.arieljorge.sgto.controller;

import com.github.arieljorge.sgto.dto.ApiResponse;
import com.github.arieljorge.sgto.dto.UsuarioCreateDto;
import com.github.arieljorge.sgto.dto.UsuarioLoginDto;
import com.github.arieljorge.sgto.dto.UsuarioLoginOutDto;
import com.github.arieljorge.sgto.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<ApiResponse<UsuarioLoginOutDto>> loginUsuario(@Valid @RequestBody UsuarioLoginDto usuarioLoginDto) {
        final UsuarioLoginOutDto response = this.usuarioService.autenticar(usuarioLoginDto);
        return ResponseEntity.ok(new ApiResponse<>(true, null, response));
    }
}
