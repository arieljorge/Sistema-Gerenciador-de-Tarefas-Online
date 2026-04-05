package com.github.arieljorge.sgto.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tarefa_usuarios")
public class TarefaUsuarios {

    @EmbeddedId
    private TarefaUsuariosID id;
}
