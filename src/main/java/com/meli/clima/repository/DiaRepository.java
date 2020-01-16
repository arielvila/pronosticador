package com.meli.clima.repository;

import com.meli.clima.model.Clima;
import com.meli.clima.model.Pronostico;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiaRepository extends CrudRepository<Pronostico, Integer> {
    Integer countAllByClima(Clima clima);
    Optional<Pronostico> findByNumero(Integer numeroDia);

    @Query(nativeQuery = true, value ="SELECT numero FROM dia WHERE intensidad_lluvia = (SELECT MAX(intensidad_lluvia) FROM dia)")
    List<Integer> findDiasLluviasMasIntensas();
}
