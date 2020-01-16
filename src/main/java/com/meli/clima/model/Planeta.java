package com.meli.clima.model;

public class Planeta {
    private String nombre;
    private Integer velocidad;
    private Integer distanciaDelSol;
    private Double angulo = 0.0;
    private Coordenadas coordenadas;

    public Planeta(String nombre, Integer velocidad, Integer distanciaDelSol) {
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.distanciaDelSol = distanciaDelSol;
        this.coordenadas = this.calcularCoordenadas();
    }

    public void moverAlDia(Integer dia) {
        this.angulo = Double.valueOf((dia * velocidad) % 360);
        if (this.angulo < 0) {
            this.angulo += 360;
        }
        this.coordenadas = calcularCoordenadas();
    }

    public Coordenadas calcularCoordenadas() {
        Double radianes = Math.toRadians(angulo);
        Double posX = Math.cos(radianes) * distanciaDelSol;
        Double posY = Math.sin(radianes) * distanciaDelSol;
        Double posXRedondeada = Math.round(posX * 100D) / 100D;
        Double posYRedondeada = Math.round(posY * 100D) / 100D;

        return new Coordenadas(posXRedondeada, posYRedondeada);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Integer velocidad) {
        this.velocidad = velocidad;
    }

    public Integer getDistanciaDelSol() {
        return distanciaDelSol;
    }

    public void setDistanciaDelSol(Integer distanciaDelSol) {
        this.distanciaDelSol = distanciaDelSol;
    }

    public Double getPosicionEnGrados() {
        return angulo;
    }

    public void setPosicionEnGrados(Double angulo) {
        this.angulo = angulo;
    }

    public Double getAngulo() {
        return angulo;
    }

    public void setAngulo(Double angulo) {
        this.angulo = angulo;
    }

    public Coordenadas getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(Coordenadas coordenadas) {
        this.coordenadas = coordenadas;
    }
}
