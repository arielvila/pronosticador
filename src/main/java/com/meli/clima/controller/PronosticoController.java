package com.meli.clima.controller;

import com.meli.clima.model.SistemaSolar;
import com.meli.clima.model.SistemaSolarFactory;
import com.meli.clima.request.PronosticoRequest;
import com.meli.clima.service.PronosticadorVulcano;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/star-trek/pronostico")
public class PronosticoController {

    private final PronosticadorVulcano pronosticadorVulcano;

    public PronosticoController(PronosticadorVulcano pronosticadorVulcano) {
        this.pronosticadorVulcano = pronosticadorVulcano;
    }

    @PostMapping()
    public ResponseEntity<String> climaPorDia(@RequestBody PronosticoRequest request) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Integer dias = request.getAnios() * 365;
                SistemaSolar sistemaSolar = SistemaSolarFactory.getSistemaSolarStarTrek();
                pronosticadorVulcano.generarPronostico(sistemaSolar, dias);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        return ResponseEntity.ok("Se generara el pronostico para los proximos " + request.getAnios() + " años");
    }

}
