package br.com.solinftec.treinamentospringboot.repository;

import br.com.solinftec.treinamentospringboot.model.Cooperativa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CooperativaRepository extends JpaRepository<Cooperativa, Long> {

    @Query("select c from Cooperativa c where lower(c.nome) like concat('%',lower(:search),'%') ")
    Page<Cooperativa> findAllPaged(Pageable pageable, @Param("search") String searc);
}
