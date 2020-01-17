package com.meli.clima;

import com.meli.clima.repository.PronosticoRepository;
import com.meli.clima.service.GeneradorVulcano;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GeneradorVulcanoTests {

	@Autowired
	GeneradorVulcano generadorVulcano;

	@Autowired
	PronosticoRepository pronosticoRepository;

	@Test
	void cantInicialDeDiasInicialesMasDiasGeneradosEsIgualACantFinalDeDias() {
		Optional<Integer> ultimoDiaOptional = pronosticoRepository.findUltimoDia();
		Integer cantidadDiasInicial = ultimoDiaOptional.isPresent() ? ultimoDiaOptional.get() + 1 : 0;

		generadorVulcano.generarPronosticoDias(10);

		Integer cantidadDiasFinal = pronosticoRepository.findUltimoDia().get() + 1;

		assertEquals(cantidadDiasFinal, cantidadDiasInicial + 10);
	}

	@Test
	void alGenerarProximoDiaDebeIncrementarseEn1LaCantidadDeDias() {
		Optional<Integer> ultimoDiaOptional = pronosticoRepository.findUltimoDia();
		Integer cantidadDiasInicial = ultimoDiaOptional.isPresent() ? ultimoDiaOptional.get() + 1 : 0;

		generadorVulcano.generarProximoDia();

		Integer cantidadDiasFinal = pronosticoRepository.findUltimoDia().get() + 1;

		assertEquals(cantidadDiasFinal, cantidadDiasInicial + 1);
	}

}
