package com.meli.clima.service;

import com.meli.clima.model.Coordenadas;
import com.meli.clima.model.Planeta;
import com.meli.clima.model.SistemaSolar;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SistemaSolarFactory {
    public static SistemaSolar getSistemaSolarStarTrek() {
        ArrayList planetas = new ArrayList();
        planetas.add(new Planeta("Ferengi", -1, 500));
        planetas.add(new Planeta("Betasoide", -3, 2000));
        planetas.add(new Planeta("Vulcano", 5, 1000));
        Coordenadas coordenadasSol = new Coordenadas(0.0, 0.0);

        return new SistemaSolar(planetas, coordenadasSol);
    }
}
