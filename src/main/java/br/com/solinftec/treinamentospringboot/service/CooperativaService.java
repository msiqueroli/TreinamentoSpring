package br.com.solinftec.treinamentospringboot.service;

import br.com.solinftec.treinamentospringboot.configuration.TreinamentoDefaultException;
import br.com.solinftec.treinamentospringboot.dto.cooperativa.CooperativaDto;
import br.com.solinftec.treinamentospringboot.dto.cooperativa.GetAllCooperativaDto;
import br.com.solinftec.treinamentospringboot.dto.cooperativa.SaveCooperativaDto;
import br.com.solinftec.treinamentospringboot.model.Cooperativa;
import br.com.solinftec.treinamentospringboot.model.Fazendeiro;
import br.com.solinftec.treinamentospringboot.repository.CooperativaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CooperativaService {

    private final CooperativaRepository repository;

    public List<GetAllCooperativaDto> getAll() {
        return repository.findAll()
                .stream()
                .map(cooperativa -> new GetAllCooperativaDto(cooperativa))
                .collect(Collectors.toList());
    }

    public List<Fazendeiro> getFazendeirosDaCooperativa(Long idCooperativa) {

        Optional<Cooperativa> cooperativa = repository.findById(idCooperativa);

        if(cooperativa.isPresent()) {
            return cooperativa.get().getFazendeiros();
        }

        return new ArrayList<>();
    }

    public SaveCooperativaDto save(SaveCooperativaDto saveCooperativaDto) {
        Cooperativa cooperativa = saveCooperativaDto.pegarModel();
        repository.save(cooperativa);
        saveCooperativaDto.setId(cooperativa.getId());
        return saveCooperativaDto;
    }

    public SaveCooperativaDto update(SaveCooperativaDto saveCooperativaDto) throws Exception {

        Optional<Cooperativa> cooperativa = repository.findById(saveCooperativaDto.getId());

        if(cooperativa.isPresent()) {
            repository.save(saveCooperativaDto.pegarModel());
            return saveCooperativaDto;
        } else {
            throw new Exception("COOPERATIVA_NOT_FOUND");
        }
    }

    public void deletar(Long idCooperativa) throws Exception {

        Optional<Cooperativa> cooperativa = repository.findById(idCooperativa);

        if(cooperativa.isPresent()) {
            repository.delete(cooperativa.get());
        } else {
            throw new Exception("COOPERATIVA_NOT_FOUND");
        }
    }

    public CooperativaDto getCooperativa(Long idCooperativa) throws TreinamentoDefaultException {
        Cooperativa cooperativa = repository.findById(idCooperativa)
                .orElseThrow(() -> new TreinamentoDefaultException("COOPERATIVA_NOT_FOUND"));
        return new CooperativaDto(cooperativa);
    }

    public Page<CooperativaDto> getPage(Pageable pageable, String search) {
        Page<Cooperativa> page = repository.findAllPaged(pageable, search);
        return page.map(cooperativa -> new CooperativaDto(cooperativa));
    }

    public List<CooperativaDto> getCooperativasAtivas() {
        return repository.findCooperativaByAtivoEqualsAndIdEqualsOrderById(true, 3L)
                .stream().map(CooperativaDto::new)
                .collect(Collectors.toList());
    }
}
