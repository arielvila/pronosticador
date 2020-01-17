package com.meli.clima.controller;

import com.meli.clima.model.Clima;
import com.meli.clima.model.Pronostico;
import com.meli.clima.model.SistemaSolar;
import com.meli.clima.response.GenerarPronosticoResponse;
import com.meli.clima.service.GeneradorVulcano;
import com.meli.clima.service.SistemaSolarFactory;
import com.meli.clima.repository.PronosticoRepository;
import com.meli.clima.response.ListaIntensosResponse;
import com.meli.clima.response.PronosticoDiaResponse;
import com.meli.clima.response.CantidadClimaResponse;
import com.meli.clima.service.PronosticadorVulcano;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/startrek/pronostico")
@Api(value = "Pronostico Vulcano", description=" ", tags = { "Pronostico Vulcano" })
public class PronosticoVulcanoController {

    private final PronosticoRepository pronosticoRepository;
    private final PronosticadorVulcano pronosticadorVulcano;
    private final GeneradorVulcano generadorVulcano;

    public PronosticoVulcanoController(PronosticoRepository pronosticoRepository, PronosticadorVulcano pronosticadorVulcano, GeneradorVulcano generadorVulcano) {
        this.pronosticoRepository = pronosticoRepository;
        this.pronosticadorVulcano = pronosticadorVulcano;
        this.generadorVulcano = generadorVulcano;
    }

    @PostMapping(value = "/generar", produces = "application/json")
    @ApiOperation(value="Generar pronosticos", tags = { "Pronostico Vulcano" })
    public ResponseEntity<GenerarPronosticoResponse> generarPronosticosPorAnio(@RequestParam("anios") Integer anios) {
        generadorVulcano.generarProximosAniosAsync(anios);
        GenerarPronosticoResponse response = new GenerarPronosticoResponse("Se generara el pronostico para los proximos " + anios + " años");

        return ResponseEntity.ok(response);
    }

    @GetMapping(produces = "application/json")
    @ApiOperation(value="Get pronostico", tags = { "Pronostico Vulcano" })
    public ResponseEntity<PronosticoDiaResponse> climaPorDia(@RequestParam("dia") Integer dia) {
        Optional<Pronostico> pronosticoOptional = pronosticoRepository.findByDia(dia);
        Pronostico pronostico;
        if (pronosticoOptional.isPresent()) {
            pronostico = pronosticoOptional.get();
        } else {
            SistemaSolar sistemaSolar = SistemaSolarFactory.getSistemaSolarStarTrek();
            pronostico = this.pronosticadorVulcano.pronosticarClima(sistemaSolar, dia);
        }

        PronosticoDiaResponse response = new PronosticoDiaResponse(pronostico.getDia(), pronostico.getClima());

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/sequia", produces = "application/json")
    @ApiOperation(value="Get dias sequia", tags = { "Pronostico Vulcano" })
    public ResponseEntity<CantidadClimaResponse> diasSequia() {
        Integer cantidad = pronosticoRepository.countAllByClima(Clima.SEQUIA);
        CantidadClimaResponse response = new CantidadClimaResponse(cantidad);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/lluviosos", produces = "application/json")
    @ApiOperation(value="Get dias lluvia", tags = { "Pronostico Vulcano" })
    public ResponseEntity<CantidadClimaResponse> diasLluvia() {
        Integer cantidad = pronosticoRepository.countAllByClima(Clima.LLUVIA);
        CantidadClimaResponse response = new CantidadClimaResponse(cantidad);

        return ResponseEntity.ok(response);
    }
    @GetMapping(value = "/optimos", produces = "application/json")
    @ApiOperation(value="Get dias clima optimo", tags = { "Pronostico Vulcano" })
    public ResponseEntity<CantidadClimaResponse> diasOptimos() {
        Integer cantidad = pronosticoRepository.countAllByClima(Clima.OPTIMO);
        CantidadClimaResponse response = new CantidadClimaResponse(cantidad);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/lluviasintensas/list", produces = "application/json")
    @ApiOperation(value="Get lista dias lluvia intensa", tags = { "Pronostico Vulcano" })
    public ResponseEntity<ListaIntensosResponse> listaIntensos() {
        List<Integer> diasIntensos = pronosticoRepository.findDiasLluviasMasIntensas();
        ListaIntensosResponse response = new ListaIntensosResponse(diasIntensos.size(), diasIntensos);

        return ResponseEntity.ok(response);
    }

}
