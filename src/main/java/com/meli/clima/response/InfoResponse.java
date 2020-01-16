package com.meli.clima.response;

import java.util.List;

public class InfoResponse {
    private Integer diasSequia;
    private Integer diasClimaLluvioso;
    private Integer diasClimaOptimo;
    private List<Integer> diasLluviaIntensa;

    public InfoResponse() {
    }

    public Integer getDiasSequia() {
        return diasSequia;
    }

    public void setDiasSequia(Integer diasSequia) {
        this.diasSequia = diasSequia;
    }

    public Integer getDiasClimaLluvioso() {
        return diasClimaLluvioso;
    }

    public void setDiasClimaLluvioso(Integer diasClimaLluvioso) {
        this.diasClimaLluvioso = diasClimaLluvioso;
    }

    public Integer getDiasClimaOptimo() {
        return diasClimaOptimo;
    }

    public void setDiasClimaOptimo(Integer diasClimaOptimo) {
        this.diasClimaOptimo = diasClimaOptimo;
    }

    public List<Integer> getDiasLluviaIntensa() {
        return diasLluviaIntensa;
    }

    public void setDiasLluviaIntensa(List<Integer> diasLluviaIntensa) {
        this.diasLluviaIntensa = diasLluviaIntensa;
    }
}
