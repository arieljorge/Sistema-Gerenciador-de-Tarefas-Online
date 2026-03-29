package com.github.arieljorge.sgto.service;

import com.github.arieljorge.sgto.dto.UsuarioCreateDto;
import com.github.arieljorge.sgto.dto.UsuarioLoginDto;
import com.github.arieljorge.sgto.dto.UsuarioLoginOutDto;

public interface UsuarioService {
    void cadastrarUsuario(UsuarioCreateDto usuarioCreateDto);
    UsuarioLoginOutDto autenticar(UsuarioLoginDto usuarioLoginDto);
}
