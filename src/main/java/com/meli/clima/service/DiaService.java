package com.meli.clima.service;

import com.meli.clima.model.Pronostico;
import com.meli.clima.model.SistemaSolar;
import com.meli.clima.repository.DiaRepository;
import org.springframework.stereotype.Service;

@Service
public class DiaService {

    private final PronosticadorVulcano pronosticadorVulcano;
    private final DiaRepository diaRepository;

    public DiaService(PronosticadorVulcano pronosticadorVulcano, DiaRepository diaRepository) {
        this.pronosticadorVulcano = pronosticadorVulcano;
        this.diaRepository = diaRepository;
    }

    public void generarPronostico(SistemaSolar sistemaSolar, Integer cantidadDias) {
        for (int i=0; i < cantidadDias; i++) {
            Pronostico pronostico = pronosticadorVulcano.pronosticarClima(sistemaSolar, i);
            diaRepository.save(pronostico);
        }
    }
}
