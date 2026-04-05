package com.github.arieljorge.sgto.service.impl;

import com.github.arieljorge.sgto.entity.Quadro;
import com.github.arieljorge.sgto.repository.QuadroRepository;
import com.github.arieljorge.sgto.service.QuadroService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuadroServiceImpl implements QuadroService {

    private final QuadroRepository quadroRepository;

    @Override
    public List<Quadro> obterQuadros() {
        return this.quadroRepository.findAll();
    }

    @Override
    @Transactional
    public Quadro cadastrarQuadro(String nome) {
        return this.quadroRepository.save(new Quadro(null, nome));
    }

    @Override
    @Transactional
    public void removerQuadro(Short id) {
        this.quadroRepository.removeById(id);
    }
}
