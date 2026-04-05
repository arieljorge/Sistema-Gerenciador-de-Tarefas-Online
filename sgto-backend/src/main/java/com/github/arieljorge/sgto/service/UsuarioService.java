package com.github.arieljorge.sgto.service;

import com.github.arieljorge.sgto.dto.UsuarioCreateDto;
import com.github.arieljorge.sgto.dto.UsuarioLoginDto;
import com.github.arieljorge.sgto.dto.UsuarioLoginOutDto;
import com.github.arieljorge.sgto.dto.UsuarioSimpleDto;

import java.util.List;

public interface UsuarioService {
    void cadastrarUsuario(UsuarioCreateDto usuarioCreateDto);
    UsuarioLoginOutDto autenticar(UsuarioLoginDto usuarioLoginDto);
    List<UsuarioSimpleDto> obterUsuarios();
}
