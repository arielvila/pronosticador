package com.meli.clima.service;

import com.meli.clima.model.Pronostico;
import com.meli.clima.model.SistemaSolar;

public interface PronosticadorInterface {
    Pronostico pronosticarClima(SistemaSolar sistemaSolar, Integer dia);
    Boolean haySequia(SistemaSolar sistemaSolar);
    Boolean hayLluvia(SistemaSolar sistemaSolar);
    Double getIntensidadLluvia(SistemaSolar sistemaSolar);
    Boolean hayClimaOptimo(SistemaSolar sistemaSolar);
}
