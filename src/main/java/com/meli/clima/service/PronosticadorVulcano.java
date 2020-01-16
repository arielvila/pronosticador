package com.meli.clima.service;

import com.meli.clima.model.*;
import com.meli.clima.repository.DiaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PronosticadorVulcano implements Pronosticador {

    private final DiaRepository diaRepository;
    private final GeometriaService geometriaService;

    public PronosticadorVulcano(GeometriaService geometriaService, DiaRepository diaRepository) {
        this.diaRepository = diaRepository;
        this.geometriaService = geometriaService;
    }

    public Pronostico pronosticarClima(SistemaSolar sistemaSolar, Integer dia) {
        sistemaSolar.setDia(dia);

        if (this.haySequia(sistemaSolar)) {
            return new Pronostico(dia, Clima.SEQUIA);
        } else if (this.hayLluvia(sistemaSolar)) {
            return new Pronostico(dia, Clima.LLUVIA, getIntensidadLluvia(sistemaSolar));
        } else if (this.hayClimaOptimo(sistemaSolar)) {
            return new Pronostico(dia, Clima.OPTIMO);
        }

        return new Pronostico(dia, Clima.NORMAL);
    }

    public Boolean haySequia(SistemaSolar sistemaSolar) {
        ArrayList<Planeta> planetas = sistemaSolar.getPlanetas();
        Coordenadas coordenadasPlaneta1 = planetas.get(0).getCoordenadas();
        Coordenadas coordenadasPlaneta2 = planetas.get(1).getCoordenadas();
        Coordenadas coordenadasPlaneta3 = planetas.get(2).getCoordenadas();
        Coordenadas coordenadasSol = sistemaSolar.getCoordenadasSol();

        Boolean planetasAlineados = geometriaService.estanAlineados(coordenadasPlaneta1, coordenadasPlaneta2, coordenadasPlaneta3);
        Boolean planetasAlineadosAlSol = geometriaService.estanAlineados(coordenadasPlaneta1, coordenadasPlaneta2, coordenadasSol);

        return planetasAlineados && planetasAlineadosAlSol;
    }

    public Boolean hayLluvia(SistemaSolar sistemaSolar) {
        ArrayList<Planeta> planetas = sistemaSolar.getPlanetas();
        Coordenadas coordenadasPlaneta1 = planetas.get(0).getCoordenadas();
        Coordenadas coordenadasPlaneta2 = planetas.get(1).getCoordenadas();
        Coordenadas coordenadasPlaneta3 = planetas.get(2).getCoordenadas();
        Coordenadas coordenadasSol = sistemaSolar.getCoordenadasSol();

        return geometriaService.estaAdentroDelTriangulo(coordenadasPlaneta1, coordenadasPlaneta2, coordenadasPlaneta3, coordenadasSol);
    }

    public Double getIntensidadLluvia(SistemaSolar sistemaSolar) {
        ArrayList<Planeta> planetas = sistemaSolar.getPlanetas();
        Coordenadas coordenadasPlaneta1 = planetas.get(0).getCoordenadas();
        Coordenadas coordenadasPlaneta2 = planetas.get(1).getCoordenadas();
        Coordenadas coordenadasPlaneta3 = planetas.get(2).getCoordenadas();

        return geometriaService.calcularPerimetroTriangulo(coordenadasPlaneta1, coordenadasPlaneta2, coordenadasPlaneta3);
    }

    public Boolean hayClimaOptimo(SistemaSolar sistemaSolar) {
        ArrayList<Planeta> planetas = sistemaSolar.getPlanetas();
        Coordenadas coordenadasPlaneta1 = planetas.get(0).getCoordenadas();
        Coordenadas coordenadasPlaneta2 = planetas.get(1).getCoordenadas();
        Coordenadas coordenadasPlaneta3 = planetas.get(2).getCoordenadas();
        Coordenadas coordenadasSol = sistemaSolar.getCoordenadasSol();

        Boolean planetasAlineados = geometriaService.estanAlineados(coordenadasPlaneta1, coordenadasPlaneta2, coordenadasPlaneta3);
        Boolean planetasAlineadosAlSol = geometriaService.estanAlineados(coordenadasPlaneta1, coordenadasPlaneta2, coordenadasSol);

        return planetasAlineados && !planetasAlineadosAlSol;
    }

    //TODO SACAR ESTO DE ACA
    public void generarPronostico(SistemaSolar sistemaSolar, Integer anios) {
        int cantidadDias = 365 * anios;
        for (int i=0; i < cantidadDias; i++) {
            Pronostico pronostico = pronosticarClima(sistemaSolar, i);
            diaRepository.save(pronostico);
        }
    }
}
