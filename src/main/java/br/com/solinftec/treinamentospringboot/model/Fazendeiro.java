package br.com.solinftec.treinamentospringboot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "FAZENDEIRO")
@Data
@NoArgsConstructor
public class Fazendeiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TELEFONE")
    private String telefone;

    @ManyToMany
    @JsonBackReference
    @JoinTable(name = "COOPERATIVA_FAZENDEIRO",
            joinColumns={@JoinColumn(name = "ID_FAZENDEIRO", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ID_COOPERATIVA", referencedColumnName = "ID")})
    private List<Cooperativa> cooperativas;

    @OneToMany(mappedBy = "fazendeiro")
    @JsonBackReference
    private List<Fazenda> fazendas;

    public Fazendeiro(Long id) {
        this.id = id;
    }
}
