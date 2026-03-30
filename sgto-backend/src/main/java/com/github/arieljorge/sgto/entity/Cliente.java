package com.github.arieljorge.sgto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente")
public class Cliente {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Size(max = 100)
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "ativo")
    private Boolean ativo;

    @Size(max = 100)
    @Column(name = "db_host")
    private String dbHost;

    @Size(max = 50)
    @Column(name = "db_username")
    private String dbUsername;

    @Size(max = 50)
    @Column(name = "db_password")
    private String dbPassword;
}
