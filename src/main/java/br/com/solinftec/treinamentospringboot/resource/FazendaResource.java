package br.com.solinftec.treinamentospringboot.resource;

import br.com.solinftec.treinamentospringboot.dto.fazenda.GetAllFazendaDto;
import br.com.solinftec.treinamentospringboot.service.FazendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fazenda")
@RequiredArgsConstructor
public class FazendaResource {

    private final FazendaService service;

    @GetMapping("")
    public ResponseEntity<List<GetAllFazendaDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

}
