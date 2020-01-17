package com.meli.clima.service;

import com.meli.clima.model.Pronostico;
import com.meli.clima.model.SistemaSolar;
import com.meli.clima.repository.PronosticoRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GeneradorVulcano {

    private final PronosticadorVulcano pronosticadorVulcano;
    private final PronosticoRepository pronosticoRepository;
    private final SistemaSolar sistemaSolar;

    public GeneradorVulcano(PronosticadorVulcano pronosticadorVulcano, PronosticoRepository pronosticoRepository) {
        this.pronosticadorVulcano = pronosticadorVulcano;
        this.pronosticoRepository = pronosticoRepository;
        this.sistemaSolar = SistemaSolarFactory.getSistemaSolarStarTrek();
    }

    public void generarProximosAniosAsync(Integer anios) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Integer cantidadDias = anios * 365;
                generarPronosticoDias(cantidadDias);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    public synchronized void generarPronosticoDias(Integer cantidadDias) {
        Integer proximoDia;
        Optional<Integer> proximoDiaOptional = pronosticoRepository.findUltimoDia();
        if (proximoDiaOptional.isPresent()) {
            proximoDia = proximoDiaOptional.get() + 1;
        } else {
            proximoDia = 0;
        }

        for (int i = proximoDia; i < (cantidadDias + proximoDia); i++) {
            Pronostico pronostico = pronosticadorVulcano.pronosticarClima(sistemaSolar, i);
            pronosticoRepository.save(pronostico);
        }
    }

    @Scheduled(cron = "${cronProximoDia}")
    public void generarProximoDia() {
        generarPronosticoDias(1);
    }

}
