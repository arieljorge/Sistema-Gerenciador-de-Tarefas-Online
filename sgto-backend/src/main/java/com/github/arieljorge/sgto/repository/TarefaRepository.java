package com.github.arieljorge.sgto.repository;

import com.github.arieljorge.sgto.entity.Tarefa;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

    @Modifying
    @Transactional
    void deleteAllById(Integer idTarefa);

    List<Tarefa> findAllByIdQuadro(Short idQuadro, Sort id);
}
