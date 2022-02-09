package br.com.solinftec.treinamentospringboot.service;

import br.com.solinftec.treinamentospringboot.dto.fazendeiro.FazendeiroDto;
import br.com.solinftec.treinamentospringboot.dto.fazendeiro.FazendeiroWithFazendaDto;
import br.com.solinftec.treinamentospringboot.model.Fazendeiro;
import br.com.solinftec.treinamentospringboot.repository.FazendeiroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FazendeiroService {

    private final FazendeiroRepository repository;


    public FazendeiroWithFazendaDto getFazendeiro(Long idFazendeiro) throws Exception {

        Optional<Fazendeiro> fazendeiro = repository.findById(idFazendeiro);

        if(fazendeiro.isPresent()) {
            return new FazendeiroWithFazendaDto(fazendeiro.get());
        } else {
            throw new Exception("FAZENDEIRO_NOT_FOUND");
        }


    }
}
