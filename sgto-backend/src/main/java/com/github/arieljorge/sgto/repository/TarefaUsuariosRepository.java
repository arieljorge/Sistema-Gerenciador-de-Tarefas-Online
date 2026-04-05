package com.github.arieljorge.sgto.repository;

import com.github.arieljorge.sgto.entity.TarefaUsuarios;
import com.github.arieljorge.sgto.entity.TarefaUsuariosID;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface TarefaUsuariosRepository extends JpaRepository<TarefaUsuarios, TarefaUsuariosID> {
    List<TarefaUsuarios> findAllById_IdTarefa(Integer idIdTarefa);

    @Modifying
    @Transactional
    void deleteAllById_IdTarefa(Integer idTarefa);
}
