package com.meli.clima.model;

import javax.persistence.*;

@Entity
@Table(name = "pronostico")
public class Pronostico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dia", unique = true)
    private Integer dia;

    @Column(name = "clima")
    private Clima clima;

    @Column(name = "intensidad_lluvia")
    private Double intensidadLluvia;

    public Pronostico() {

    }

    public Pronostico(Integer dia, Clima clima) {
        this.dia = dia;
        this.clima = clima;
    }

    public Pronostico(Integer dia, Clima clima, Double intensidadLluvia) {
        this.dia = dia;
        this.clima = clima;
        this.intensidadLluvia = intensidadLluvia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Clima getClima() {
        return clima;
    }

    public void setClima(Clima clima) {
        this.clima = clima;
    }

    public Double getIntensidadLluvia() {
        return intensidadLluvia;
    }

    public void setIntensidadLluvia(Double intensidadLluvia) {
        this.intensidadLluvia = intensidadLluvia;
    }
}
