package com.meli.clima.model;

import java.util.ArrayList;

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
