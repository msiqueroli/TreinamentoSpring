package br.com.solinftec.treinamentospringboot.resource;

import br.com.solinftec.treinamentospringboot.configuration.TreinamentoDefaultException;
import br.com.solinftec.treinamentospringboot.dto.cooperativa.CooperativaDto;
import br.com.solinftec.treinamentospringboot.dto.cooperativa.GetAllCooperativaDto;
import br.com.solinftec.treinamentospringboot.dto.cooperativa.SaveCooperativaDto;
import br.com.solinftec.treinamentospringboot.dto.fazenda.GetAllFazendaDto;
import br.com.solinftec.treinamentospringboot.model.Cooperativa;
import br.com.solinftec.treinamentospringboot.model.Fazendeiro;
import br.com.solinftec.treinamentospringboot.service.CooperativaService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cooperativa")
@RequiredArgsConstructor
public class CooperativaResource {

    private static final Logger logger = LoggerFactory.getLogger(Cooperativa.class);

    private final CooperativaService service;

    @GetMapping("")
    public ResponseEntity<List<GetAllCooperativaDto>> getAll() {
        try {
            return ResponseEntity.ok().body(service.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{idCooperativa}")
    public ResponseEntity<CooperativaDto> getCooperativa(@PathVariable("idCooperativa") Long idCooperativa) throws TreinamentoDefaultException {
        return ResponseEntity.ok().body(service.getCooperativa(idCooperativa));
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<CooperativaDto>> getPage(Pageable pageable, @RequestParam("search") String search) {
        try {
            return ResponseEntity.ok().body(service.getPage(pageable, search));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/fazendeiros/{idCooperativa}")
    public ResponseEntity<List<Fazendeiro>> getFazendeirosDaCooperativa(@PathVariable("idCooperativa") Long id) {

        logger.info("Pegando todos fazendeiros da cooperativa id {}", id);

        try {
            return ResponseEntity.ok().body(service.getFazendeirosDaCooperativa(id));
        } catch (Exception e) {
            logger.error("Erro ao buscar fazendeiros da cooperativa id {}, erro: {}", id, e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<SaveCooperativaDto> save(@RequestBody @Valid SaveCooperativaDto saveCooperativaDto) {
        try {
            return ResponseEntity.ok().body(service.save(saveCooperativaDto));
        } catch (Exception e) {
            logger.error("Erro ao salvar saveCooperativaDto: {}", saveCooperativaDto);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<SaveCooperativaDto> update(@RequestBody SaveCooperativaDto saveCooperativaDto) {
        try {
            return ResponseEntity.ok().body(service.update(saveCooperativaDto));
        } catch (Exception e) {
            logger.error("Erro ao atualizar saveCooperativaDto: {}, error: {}", saveCooperativaDto, e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{idCooperativa}")
    public ResponseEntity<?> delete(@PathVariable("idCooperativa") Long idCooperativa) {
        try {
            service.deletar(idCooperativa);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Erro ao deletar cooperativa: {}, error: {}", idCooperativa, e.getMessage());

            if(e.getMessage().equals("COOPERATIVA_NOT_FOUND"))
                return ResponseEntity.notFound().build();

            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<CooperativaDto>> getCooperativasAtivas() {
        return ResponseEntity.ok().body(service.getCooperativasAtivas());
    }

    @GetMapping("/rest/fazendas")
    public ResponseEntity<List<GetAllFazendaDto>> getAllFazendasByHttpRequest() {
        return ResponseEntity.ok().body(service.getAllFazendasByHttpRequest());
    }
}
