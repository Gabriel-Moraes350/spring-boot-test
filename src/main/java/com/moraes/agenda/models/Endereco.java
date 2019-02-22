package com.moraes.agenda.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moraes.agenda.enums.TipoEndereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "contato_id")
    @JsonIgnore
    private Contato contato;

    private String rua;

    @Enumerated(EnumType.STRING)
    private TipoEndereco tipoEndereco;

    private String bairro;

    @Column(length = 300)
    private String complemento;

    private String cidade;

    @Column(length = 2)
    private String uf;
}
