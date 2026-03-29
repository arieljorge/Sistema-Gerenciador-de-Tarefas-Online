package com.github.arieljorge.sgto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class UsuariosRolesID implements Serializable {

    @Column(name = "id_usuario")
    private UUID idUsuario;

    @Column(name = "id_role")
    private Integer idRole;
}
