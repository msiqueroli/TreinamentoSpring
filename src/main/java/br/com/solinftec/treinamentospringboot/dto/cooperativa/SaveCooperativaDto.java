package br.com.solinftec.treinamentospringboot.dto.cooperativa;

import br.com.solinftec.treinamentospringboot.model.Cooperativa;
import br.com.solinftec.treinamentospringboot.model.Fazendeiro;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class SaveCooperativaDto {

    private Long id;
    @NotNull(message = "NOME_COULD_NOT_BE_NULL")
    private String nome;
    @NotNull(message = "EMAIL_COULD_NOT_BE_NULL")
    @Email(message = "INVALID_EMAIL:${validatedValue}")
    private String email;
    private List<Long> fazendeiros;

    public Cooperativa pegarModel() {
        Cooperativa cooperativa = new Cooperativa();
        cooperativa.setAtivo(true);
        cooperativa.setNome(this.nome);
        cooperativa.setEmail(this.email);
        cooperativa.setId(this.id);
        cooperativa.setFazendeiros(fazendeiros.stream().map(Fazendeiro::new).collect(Collectors.toList()));
        return cooperativa;
    }

}
