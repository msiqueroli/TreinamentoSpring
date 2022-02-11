package br.com.solinftec.treinamentospringboot.dto.cooperativa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CooperativaParaSalvarDto {
    private Long id;
    private List<Long> fazendeiros;
    private String nome;
    private String email;
}
