package com.meli.clima.model;

import javax.persistence.*;

@Entity
@Table(name = "dia")
public class Pronostico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "clima")
    private Clima clima;

    @Column(name = "intensidad_lluvia")
    private Double intensidadLluvia;

    public Pronostico() {

    }

    public Pronostico(Integer numero, Clima clima) {
        this.numero = numero;
        this.clima = clima;
    }

    public Pronostico(Integer numero, Clima clima, Double intensidadLluvia) {
        this.numero = numero;
        this.clima = clima;
        this.intensidadLluvia = intensidadLluvia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
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
