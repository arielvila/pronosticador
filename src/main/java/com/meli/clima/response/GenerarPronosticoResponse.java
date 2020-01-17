package com.meli.clima.response;

public class GenerarPronosticoResponse {
    private String mensaje;

    public GenerarPronosticoResponse(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
