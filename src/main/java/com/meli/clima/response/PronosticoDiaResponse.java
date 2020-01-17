package com.meli.clima.response;

import com.meli.clima.model.Clima;

public class PronosticoDiaResponse {
    private Integer dia;
    private Clima clima;

    public PronosticoDiaResponse(Integer dia, Clima clima) {
        this.dia = dia;
        this.clima = clima;
    }

    public Integer getDia() {
        return dia;
    }

    public Clima getClima() {
        return clima;
    }
}
