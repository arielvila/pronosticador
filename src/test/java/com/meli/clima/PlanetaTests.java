package com.meli.clima;

import com.meli.clima.model.Coordenadas;
import com.meli.clima.model.Planeta;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlanetaTests {

	@Test
	void unPlanetaDeVel1DebeTenerElAnguloIgualAlDiaMod360() {
		Planeta planeta = new Planeta("planeta", 1, 1);
		planeta.moverAlDia(365);

		assertEquals(planeta.getAngulo(), 5);
	}

	@Test
	void unPlanetaDeVelNegativaDebeTenerElAnguloPositivo() {
		Planeta planeta = new Planeta("planeta", -1, 1);
		planeta.moverAlDia(365);

		assertEquals(planeta.getAngulo(), 355);
	}

	@Test
	void alTenerAngulo0LaCoordenadaXDebeSerLaDistancia() {
		Planeta planeta = new Planeta("planeta", 1, 1);
		Coordenadas coordenadas = planeta.calcularCoordenadas();

		assertEquals(coordenadas.getX(), 1);
	}

	@Test
	void alTenerAngulo90LaCoordenadaYDebeSerLaDistancia() {
		Planeta planeta = new Planeta("planeta", 1, 1);
		planeta.moverAlDia(90);
		Coordenadas coordenadas = planeta.calcularCoordenadas();

		assertEquals(coordenadas.getY(), 1);
	}

	@Test
	void alTenerAngulo180LaCoordenadaXDebeSerLaDistanciaNegativa() {
		Planeta planeta = new Planeta("planeta", 1, 1);
		planeta.moverAlDia(180);
		Coordenadas coordenadas = planeta.calcularCoordenadas();

		assertEquals(coordenadas.getX(), -1);
	}

}
