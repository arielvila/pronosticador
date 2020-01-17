package com.meli.clima.response;

import java.util.List;

public class ListaIntensosResponse {
    private Integer cantidad;
    private List<Integer> dias;

    public ListaIntensosResponse(Integer cantidad, List<Integer> dias) {
        this.cantidad = cantidad;
        this.dias = dias;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public List<Integer> getDias() {
        return dias;
    }
}
