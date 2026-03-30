package com.github.arieljorge.sgto.entity;

import com.github.arieljorge.sgto.config.PlataformaExternaEnumConverter;
import com.github.arieljorge.sgto.enumerator.PlataformaExterna;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produto_situacao")
public class ProdutoSituacao {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Size(max = 100)
    @Column(name = "nome", nullable = false)
    private String nome;

    @Size(max = 50)
    @Column(name = "id_externo")
    private String idExterno;

    @Column(name = "plataforma_origem", nullable = false)
    @Convert(converter = PlataformaExternaEnumConverter.class)
    private PlataformaExterna plataformaOrigem;
}
