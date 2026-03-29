package com.github.arieljorge.sgto.repository;

import com.github.arieljorge.sgto.entity.UsuariosRoles;
import com.github.arieljorge.sgto.entity.UsuariosRolesID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UsuariosRolesRepository extends JpaRepository<UsuariosRoles, UsuariosRolesID> {
    @Query("""
        SELECT r.nome FROM Role r
        INNER JOIN UsuariosRoles ur ON ur.id.idRole = r.id
        WHERE ur.id.idUsuario = :idUsuario
    """)
    List<String> findNomeRolesByIdUsuario(@Param("idUsuario") UUID idUsuario);
}
