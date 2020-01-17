package com.meli.clima;

import com.meli.clima.model.*;
import com.meli.clima.service.GeometriaService;
import com.meli.clima.service.PronosticadorVulcano;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PronosticadorVulcanoTests {

    @Autowired
    PronosticadorVulcano pronosticadorVulcano;

    @Autowired
    GeometriaService geometriaService;

    @Test
    void siLosPlanetasEstanAlineadosConElSolHaySequia() {
        ArrayList planetas = new ArrayList();
        planetas.add(new Planeta("planeta1", 0, 10));
        planetas.add(new Planeta("planeta2", 0, 20));
        planetas.add(new Planeta("planeta3", 180, 30));
        SistemaSolar sistemaSolar = new SistemaSolar(planetas, new Coordenadas(0.0, 0.0));
        sistemaSolar.setDia(1);

        Boolean haySequia = pronosticadorVulcano.haySequia(sistemaSolar);

        assertTrue(haySequia);
    }

    @Test
    void siLosPlanetasFormanTrianguloYElSolEstaAdentroHayLluvia() {
        ArrayList planetas = new ArrayList();
        planetas.add(new Planeta("planeta1", 0, 10));
        planetas.add(new Planeta("planeta2", 120, 20));
        planetas.add(new Planeta("planeta3", 240, 30));
        SistemaSolar sistemaSolar = new SistemaSolar(planetas, new Coordenadas(0.0, 0.0));
        sistemaSolar.setDia(1);

        Boolean hayLluvia = pronosticadorVulcano.hayLluvia(sistemaSolar);

        assertTrue(hayLluvia);
    }

    @Test
    void siLosPlanetasFormanTrianguloYElSolNoEstaAdentroNoHayLluvia() {
        ArrayList planetas = new ArrayList();
        planetas.add(new Planeta("planeta1", 0, 10));
        planetas.add(new Planeta("planeta2", 10, 20));
        planetas.add(new Planeta("planeta3", 20, 30));
        SistemaSolar sistemaSolar = new SistemaSolar(planetas, new Coordenadas(0.0, 0.0));
        sistemaSolar.setDia(1);

        Boolean hayLluvia = pronosticadorVulcano.hayLluvia(sistemaSolar);

        assertFalse(hayLluvia);
    }

    @Test
    void siLosPlanetasEstanAlineadosSinElSolHayClimaOptimo() {
        ArrayList planetas = new ArrayList();
        planetas.add(new Planeta("planeta1", 0, 10));
        planetas.add(new Planeta("planeta2", 180, 20));
        planetas.add(new Planeta("planeta3", 0, 30));
        SistemaSolar sistemaSolar = new SistemaSolar(planetas, new Coordenadas(0.0, 2.0));
        sistemaSolar.setDia(1);

        Boolean climaEstaOptimo = pronosticadorVulcano.hayClimaOptimo(sistemaSolar);

        assertTrue(climaEstaOptimo);
    }

    @Test
    void laIntesidadDeLluviaEsIgualASuPerimetroFormadoPorPlanetas() {
        ArrayList planetas = new ArrayList();
        planetas.add(new Planeta("planeta1", 90, 1));
        planetas.add(new Planeta("planeta2", 90, 3));
        planetas.add(new Planeta("planeta3", 0, 4));
        SistemaSolar sistemaSolar = new SistemaSolar(planetas, new Coordenadas(0.0, 0.0));
        sistemaSolar.setDia(1);

        Double intensidadLluvia = pronosticadorVulcano.getIntensidadLluvia(sistemaSolar);

        Coordenadas vertice1 = new Coordenadas(0.0, 1.0);
        Coordenadas vertice2 = new Coordenadas(0.0, 3.0);
        Coordenadas vertice3 = new Coordenadas(4.0, 0.0);
        Double perimetro = geometriaService.calcularPerimetroTriangulo(vertice1, vertice2, vertice3);

        assertEquals(intensidadLluvia, perimetro);
    }

    @Test
    void sihaySequiaDebePronosticarSEQUIA() {
        ArrayList planetas = new ArrayList();
        planetas.add(new Planeta("planeta1", 0, 10));
        planetas.add(new Planeta("planeta2", 0, 20));
        planetas.add(new Planeta("planeta3", 180, 30));
        SistemaSolar sistemaSolar = new SistemaSolar(planetas, new Coordenadas(0.0, 0.0));

        Pronostico pronostico = pronosticadorVulcano.pronosticarClima(sistemaSolar, 1);

        assertEquals(pronostico.getClima(), Clima.SEQUIA);
        assertEquals(pronostico.getDia(), 1);
    }

    @Test
    void sihayLluviaDebePronosticarLLUVIA() {
        ArrayList planetas = new ArrayList();
        planetas.add(new Planeta("planeta1", 0, 10));
        planetas.add(new Planeta("planeta2", 120, 20));
        planetas.add(new Planeta("planeta3", 240, 30));
        SistemaSolar sistemaSolar = new SistemaSolar(planetas, new Coordenadas(0.0, 0.0));

        Pronostico pronostico = pronosticadorVulcano.pronosticarClima(sistemaSolar, 1);

        assertEquals(pronostico.getClima(), Clima.LLUVIA);
        assertEquals(pronostico.getDia(), 1);
    }

    @Test
    void sihayClimaOptimoDebePronosticarOPTIMO() {
        ArrayList planetas = new ArrayList();
        planetas.add(new Planeta("planeta1", 0, 10));
        planetas.add(new Planeta("planeta2", 180, 20));
        planetas.add(new Planeta("planeta3", 0, 30));
        SistemaSolar sistemaSolar = new SistemaSolar(planetas, new Coordenadas(0.0, 2.0));

        Pronostico pronostico = pronosticadorVulcano.pronosticarClima(sistemaSolar, 1);

        assertEquals(pronostico.getClima(), Clima.OPTIMO);
        assertEquals(pronostico.getDia(), 1);
    }

    @Test
    void sihayLluviaDebeCalcularIntensidad() {
        ArrayList planetas = new ArrayList();
        planetas.add(new Planeta("planeta1", 0, 10));
        planetas.add(new Planeta("planeta2", 120, 20));
        planetas.add(new Planeta("planeta3", 240, 30));
        SistemaSolar sistemaSolar = new SistemaSolar(planetas, new Coordenadas(0.0, 0.0));

        Pronostico pronostico = pronosticadorVulcano.pronosticarClima(sistemaSolar, 1);

        assertNotNull(pronostico.getIntensidadLluvia());
    }

    @Test
    void siNoHayLluviaNoDebeCalcularIntensidad() {
        ArrayList planetas = new ArrayList();
        planetas.add(new Planeta("planeta1", 0, 10));
        planetas.add(new Planeta("planeta2", 180, 20));
        planetas.add(new Planeta("planeta3", 0, 30));
        SistemaSolar sistemaSolar = new SistemaSolar(planetas, new Coordenadas(0.0, 2.0));

        Pronostico pronostico = pronosticadorVulcano.pronosticarClima(sistemaSolar, 1);

        assertNull(pronostico.getIntensidadLluvia());
    }
}
