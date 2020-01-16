package com.meli.clima;

import com.meli.clima.service.GeometriaService;
import com.meli.clima.model.Coordenadas;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GeometriaTests {

	@Autowired
	GeometriaService geometriaService;

	@Test
	void losPuntosEstanAlineados() {
		Coordenadas punto1 = new Coordenadas(1.0,1.0);
		Coordenadas punto2 = new Coordenadas(-4.0,-4.0);
		Coordenadas punto3 = new Coordenadas(0.0,0.0);
		Boolean estanAlineados = geometriaService.estanAlineados(punto1, punto2, punto3);

		assertTrue(estanAlineados);
	}

	@Test
	void losPuntosFormanTriangulo() {
		Coordenadas punto1 = new Coordenadas(1.0,0.0);
		Coordenadas punto2 = new Coordenadas(-4.0,-4.0);
		Coordenadas punto3 = new Coordenadas(0.0,0.0);
		Boolean estanAlineados = geometriaService.estanAlineados(punto1, punto2, punto3);

		assertFalse(estanAlineados);
	}

	@Test
	void elPuntoEstaDentroDelTriangulo() {
		Coordenadas vertice1 = new Coordenadas(1.0, -1.0);
		Coordenadas vertice2 = new Coordenadas(-1.0, -1.0);
		Coordenadas vertice3 = new Coordenadas(0.0, 1.0);
		Coordenadas punto = new Coordenadas(0.0, 0.0);

		Boolean estaAdentroDelTriangulo = geometriaService.estaAdentroDelTriangulo(vertice1, vertice2, vertice3, punto);

		assertTrue(estaAdentroDelTriangulo);
	}

	@Test
	void elPuntoEstaEnElBordeDelTriangulo() {
		Coordenadas vertice1 = new Coordenadas(1.0, 0.0);
		Coordenadas vertice2 = new Coordenadas(-1.0, 0.0);
		Coordenadas vertice3 = new Coordenadas(0.0, 1.0);
		Coordenadas punto = new Coordenadas(0.0,0.0);

		Boolean estaAdentroDelTriangulo = geometriaService.estaAdentroDelTriangulo(vertice1, vertice2, vertice3, punto);

		assertTrue(estaAdentroDelTriangulo);
	}

	@Test
	void elPuntoEstaFueraDelTriangulo() {
		Coordenadas vertice1 = new Coordenadas(1.0, 2.0);
		Coordenadas vertice2 = new Coordenadas(-1.0, 2.0);
		Coordenadas vertice3 = new Coordenadas(0.0, 1.0);
		Coordenadas punto = new Coordenadas(0.0,0.0);

		Boolean estaAdentroDelTriangulo = geometriaService.estaAdentroDelTriangulo(vertice1, vertice2, vertice3, punto);

		assertFalse(estaAdentroDelTriangulo);
	}

	@Test
	void laDistanciaEntre2PuntosDelMismoEjeEsLaDiferenciaDelOtroEje() {
		Coordenadas punto1 = new Coordenadas(0.0, 0.0);
		Coordenadas punto2 = new Coordenadas(0.0, 2.0);

		Double perimetro = geometriaService.calcularDistanciaDosPuntos(punto1, punto2);

		assertEquals(perimetro, 2);
	}

	@Test
	void elPerimetroDelTrianguloEquilateroDe1DeLadoEs3() {
		Coordenadas vertice1 = new Coordenadas(0.0, 0.0);
		Coordenadas vertice2 = new Coordenadas(4.0, 0.0);
		Coordenadas vertice3 = new Coordenadas(0.0, 3.0);

		Double perimetro = geometriaService.calcularPerimetroTriangulo(vertice1, vertice2, vertice3);

		assertEquals(perimetro, 12);
	}

}
