package com.meli.clima.model;

import java.util.ArrayList;

public class SistemaSolar {
    private ArrayList<Planeta> planetas;
    private final Coordenadas coordenadasSol;
    private Integer dia = 0;

    public SistemaSolar(ArrayList<Planeta> planetas, Coordenadas coordenadasSol) {
        this.planetas = planetas;
        this.coordenadasSol = coordenadasSol;
    }

    public void setDia(Integer dia) {
        if (dia >= 0) {
            this.dia = dia;
            for (Planeta planeta : planetas) {
                planeta.moverAlDia(dia);
            }
        }
    }

    public ArrayList<Planeta> getPlanetas() {
        return planetas;
    }

    public Coordenadas getCoordenadasSol() {
        return this.coordenadasSol;
    }
}
