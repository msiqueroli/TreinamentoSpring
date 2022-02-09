package br.com.solinftec.treinamentospringboot.dto.fazenda;

import br.com.solinftec.treinamentospringboot.dto.fazendeiro.FazendeiroDto;
import br.com.solinftec.treinamentospringboot.model.Fazenda;
import lombok.Data;

@Data
public class FazendaDto {
    private Long id;
    private String descricao;
    private Float area;
    private Float latitude;
    private Float longitude;

    public FazendaDto(Fazenda fazenda) {
        this.id = fazenda.getId();
        this.descricao = fazenda.getDescricao();
        this.area = fazenda.getArea();
        this.latitude = fazenda.getLatitude();
        this.longitude = fazenda.getLongitude();
    }
}
