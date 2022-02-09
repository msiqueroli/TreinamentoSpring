package br.com.solinftec.treinamentospringboot.resource;

import br.com.solinftec.treinamentospringboot.dto.fazendeiro.FazendeiroDto;
import br.com.solinftec.treinamentospringboot.dto.fazendeiro.FazendeiroWithFazendaDto;
import br.com.solinftec.treinamentospringboot.service.FazendeiroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fazendeiro")
@RequiredArgsConstructor
public class FazendeiroResource {

    private final FazendeiroService service;

    @GetMapping("/{idFazendeiro}")
    public ResponseEntity<FazendeiroWithFazendaDto> getFazendeiro(@PathVariable("idFazendeiro") Long idFazendeiro) {
        try {
            return ResponseEntity.ok().body(service.getFazendeiro(idFazendeiro));
        } catch (Exception e) {
            if(e.getMessage().equals("FAZENDEIRO_NOT_FOUND"))
                return ResponseEntity.notFound().build();
            return ResponseEntity.badRequest().build();
        }
    }
}
