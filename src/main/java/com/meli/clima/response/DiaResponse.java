package com.meli.clima.response;

import com.meli.clima.model.Clima;

public class DiaResponse {
    private Integer dia;
    private Clima clima;

    public DiaResponse(Integer dia, Clima clima) {
        this.dia = dia;
        this.clima = clima;
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
}
