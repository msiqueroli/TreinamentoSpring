package br.com.solinftec.treinamentospringboot.service;

import br.com.solinftec.treinamentospringboot.dto.fazenda.GetAllFazendaDto;
import br.com.solinftec.treinamentospringboot.repository.FazendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FazendaService {

    private final FazendaRepository repository;

    public List<GetAllFazendaDto> getAll() {
        return repository.findAll().stream()
                .map(fazenda -> new GetAllFazendaDto(fazenda))
                .collect(Collectors.toList());
    }
}
