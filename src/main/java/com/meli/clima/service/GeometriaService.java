package com.meli.clima.service;

import com.meli.clima.model.Coordenadas;
import org.springframework.stereotype.Component;

@Component
public class GeometriaService {

    public Boolean estanAlineados(Coordenadas coordenadas1, Coordenadas coordenadas2, Coordenadas coordenadas3) {
        return calcularOrientacionEntrePuntos(coordenadas1, coordenadas2, coordenadas3) == 0;
    }

    public Boolean estaAdentroDelTriangulo(Coordenadas vertice1, Coordenadas vertice2, Coordenadas vertice3, Coordenadas punto) {
        Double orientacionTriangulo = calcularOrientacionEntrePuntos(vertice1, vertice2, vertice3);
        if (orientacionTriangulo != 0) {
            Double orientacion12 = calcularOrientacionEntrePuntos(vertice1, vertice2, punto);
            Double orientacion13 = calcularOrientacionEntrePuntos(vertice2, vertice3, punto);
            Double orientacion23 = calcularOrientacionEntrePuntos(vertice3, vertice1, punto);

            Boolean orientacionesTodasPositivas = orientacionTriangulo > 0 && orientacion12 >= 0 && orientacion13 >= 0 && orientacion23 >= 0;
            Boolean orientacionesTodasNegativas = orientacionTriangulo < 0 && orientacion12 <= 0 && orientacion12 <= 0 && orientacion23 <= 0;
            if (orientacionesTodasPositivas || orientacionesTodasNegativas) {
                return true;
            }
        }

        return false;
    }

    private Double calcularOrientacionEntrePuntos(Coordenadas coordenadas1, Coordenadas coordenadas2, Coordenadas coordenadas3) {
        return (coordenadas1.getX() - coordenadas3.getX()) * (coordenadas2.getY() - coordenadas3.getY()) - (coordenadas1.getY() - coordenadas3.getY()) * (coordenadas2.getX() - coordenadas3.getX());
    }


    public Double calcularPerimetroTriangulo(Coordenadas coordenadas1, Coordenadas coordenadas2, Coordenadas coordenadas3) {
        Double lado12 = calcularDistanciaDosPuntos(coordenadas1, coordenadas2);
        Double lado23 = calcularDistanciaDosPuntos(coordenadas1, coordenadas3);
        Double lado13 = calcularDistanciaDosPuntos(coordenadas2, coordenadas3);

        return lado12 + lado23 + lado13;
    }

    public Double calcularDistanciaDosPuntos(Coordenadas coordenadas1, Coordenadas coordenadas2) {
        Double diferenciaXAlCuadrado = Math.pow(coordenadas1.getX() - coordenadas2.getX(), 2);
        Double diferenciaYAlCuadrado = Math.pow(coordenadas1.getY() - coordenadas2.getY(), 2);

        return Math.sqrt(diferenciaXAlCuadrado + diferenciaYAlCuadrado);
    }
}
