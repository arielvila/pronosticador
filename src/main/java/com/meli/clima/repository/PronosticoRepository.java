package com.meli.clima.repository;

import com.meli.clima.model.Clima;
import com.meli.clima.model.Pronostico;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PronosticoRepository extends CrudRepository<Pronostico, Integer> {
    Integer countAllByClima(Clima clima);

    Optional<Pronostico> findByDia(Integer dia);

    @Query(nativeQuery = true, value ="SELECT MAX(dia) FROM pronostico")
    Optional<Integer> findUltimoDia();

    @Query(nativeQuery = true, value ="SELECT dia FROM pronostico WHERE intensidad_lluvia = (SELECT MAX(intensidad_lluvia) FROM pronostico)")
    List<Integer> findDiasLluviasMasIntensas();
}
