package br.com.solinftec.treinamentospringboot.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ORDEM_SERVICO")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_COOPERATIVA")
    private Cooperativa cooperativa;

}
