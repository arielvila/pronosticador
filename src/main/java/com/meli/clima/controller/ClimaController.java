package com.meli.clima.controller;

import com.meli.clima.model.Clima;
import com.meli.clima.model.Pronostico;
import com.meli.clima.model.SistemaSolar;
import com.meli.clima.model.SistemaSolarFactory;
import com.meli.clima.repository.DiaRepository;
import com.meli.clima.response.DiaResponse;
import com.meli.clima.response.InfoResponse;
import com.meli.clima.service.PronosticadorVulcano;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/star-trek/clima")
public class ClimaController {

    private final DiaRepository diaRepository;
    private final PronosticadorVulcano pronosticadorVulcano;


    public ClimaController(DiaRepository diaRepository, PronosticadorVulcano pronosticadorVulcano) {
        this.diaRepository = diaRepository;
        this.pronosticadorVulcano = pronosticadorVulcano;
    }

    @GetMapping()
    public ResponseEntity<DiaResponse> climaPorDia(@RequestParam("dia") Integer dia) {
        Optional<Pronostico> diaOptional = diaRepository.findByNumero(dia);
        Pronostico pronostico;
        if (diaOptional.isPresent()) {
            pronostico = diaOptional.get();
        } else {
            SistemaSolar sistemaSolar = SistemaSolarFactory.getSistemaSolarStarTrek();
            pronostico = this.pronosticadorVulcano.pronosticarClima(sistemaSolar, dia);
        }

        DiaResponse response = new DiaResponse(pronostico.getNumero(), pronostico.getClima());

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/sequia")
    public ResponseEntity<Integer> diasSequia() {
        return ResponseEntity.ok(diaRepository.countAllByClima(Clima.SEQUIA));
    }

    @GetMapping(value = "/lluvia")
    public ResponseEntity<Integer> diasLluvia() {
        return ResponseEntity.ok(diaRepository.countAllByClima(Clima.LLUVIA));
    }
    @GetMapping(value = "/optimo")
    public ResponseEntity<Integer> diasOptimos() {
        return ResponseEntity.ok(diaRepository.countAllByClima(Clima.OPTIMO));
    }

    @GetMapping(value = "/intensos")
    public ResponseEntity<Integer> diasIntensos() {
        return ResponseEntity.ok(diaRepository.findDiasLluviasMasIntensas().size());
    }

    @GetMapping(value = "/lista-intensos")
    public ResponseEntity<List<Integer>> listaIntensos() {
        return ResponseEntity.ok(diaRepository.findDiasLluviasMasIntensas());
    }

    @GetMapping(value = "/info-general")
    public InfoResponse climaPorDia() {
        InfoResponse info = new InfoResponse();
        info.setDiasSequia(diaRepository.countAllByClima(Clima.SEQUIA));
        info.setDiasClimaLluvioso(diaRepository.countAllByClima(Clima.LLUVIA));
        info.setDiasClimaOptimo(diaRepository.countAllByClima(Clima.OPTIMO));
        info.setDiasLluviaIntensa(diaRepository.findDiasLluviasMasIntensas());

        return info;
    }

}
