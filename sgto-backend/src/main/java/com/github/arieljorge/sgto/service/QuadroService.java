package com.github.arieljorge.sgto.service;

import com.github.arieljorge.sgto.entity.Quadro;

import java.util.List;

public interface QuadroService {
    List<Quadro> obterQuadros();
    Quadro cadastrarQuadro(String nome);
    void removerQuadro(Short id);
}
