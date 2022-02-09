package br.com.solinftec.treinamentospringboot.dto.fazendeiro;

import br.com.solinftec.treinamentospringboot.model.Fazendeiro;
import lombok.Data;


@Data
public class FazendeiroDto {
    private Long id;
    private String nome;
    private String email;
    private String telefone;

    public FazendeiroDto(Fazendeiro fazendeiro) {
        this.id = fazendeiro.getId();
        this.nome = fazendeiro.getNome();
        this.email = fazendeiro.getEmail();
        this.telefone = fazendeiro.getTelefone();
    }
}
