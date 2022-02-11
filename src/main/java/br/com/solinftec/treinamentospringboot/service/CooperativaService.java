package br.com.solinftec.treinamentospringboot.service;

import br.com.solinftec.treinamentospringboot.configuration.TreinamentoDefaultException;
import br.com.solinftec.treinamentospringboot.dto.cooperativa.CooperativaDto;
import br.com.solinftec.treinamentospringboot.dto.cooperativa.CooperativaParaSalvarDto;
import br.com.solinftec.treinamentospringboot.dto.cooperativa.GetAllCooperativaDto;
import br.com.solinftec.treinamentospringboot.dto.cooperativa.SaveCooperativaDto;
import br.com.solinftec.treinamentospringboot.dto.fazenda.GetAllFazendaDto;
import br.com.solinftec.treinamentospringboot.model.Cooperativa;
import br.com.solinftec.treinamentospringboot.model.Fazendeiro;
import br.com.solinftec.treinamentospringboot.repository.CooperativaRepository;
import br.com.solinftec.treinamentospringboot.utils.RestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CooperativaService {

    @Value("${configuration.url}")
    private String url;

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

    public List<GetAllFazendaDto> getAllFazendasByHttpRequest() {

        RestTemplate rest = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        CooperativaParaSalvarDto minhaCooperativa = new CooperativaParaSalvarDto();
        minhaCooperativa.setEmail("exemplo@email.com");
        minhaCooperativa.setNome("Cooperativa Exemplo");
        minhaCooperativa.setFazendeiros(Arrays.asList(2L));

        // Save Cooperativa
        minhaCooperativa = RestUtils.postForObject(url + "cooperativa", CooperativaParaSalvarDto.class, minhaCooperativa);

        // Get Cooperativa
        List<GetAllFazendaDto> allFazendasList = RestUtils.getForList(url+"fazenda", GetAllFazendaDto.class, "token");

        //Delete Cooperativa
        rest.delete(url+"/cooperativa/9");

        //Get a list of object
        GetAllFazendaDto[] allFazendas = rest.getForEntity(url+"fazenda", GetAllFazendaDto[].class, entity).getBody();

        //Get an object
        rest.getForObject(url+"cooperativa/1", CooperativaDto.class);

        return Arrays.stream(allFazendas).collect(Collectors.toList());
    }


}
