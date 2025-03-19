package cu.edu.cujae.gestion.postgresql.municio.core.controller;

import cu.edu.cujae.gestion.postgresql.municio.core.conexion.apiExterna.APIGeograficaMunicipio;
import cu.edu.cujae.gestion.postgresql.municio.core.dto.apiGeografica.APIGeoMunicipioDto;
import cu.edu.cujae.gestion.postgresql.municio.core.dto.recibir.PaisEstadoDto;
import cu.edu.cujae.gestion.postgresql.municio.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.municio.core.exception.APIException;
import cu.edu.cujae.gestion.postgresql.municio.core.model.Municipio;
import cu.edu.cujae.gestion.postgresql.municio.core.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/v1/gestion/1/municipio")
public class MunicipioController {
    private MunicipioService municipioService;

    @Autowired
    public MunicipioController(MunicipioService municipioService) {
        this.municipioService = municipioService;
    }

    @PostMapping("/")
    public ResponseEntity<RespuestaEstandar> insertarMunicipios(@RequestBody PaisEstadoDto paisEstadoDto) throws APIException, IOException, InterruptedException {
        APIGeoMunicipioDto apiGeoMunicipioDto = APIGeograficaMunicipio.obtenerDatosMunicipio(paisEstadoDto);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        apiGeoMunicipioDto.geonames().stream().forEach(estado -> {
            atomicInteger.incrementAndGet();
            municipioService.insertarMunicipio(new Municipio());
        });
        return ResponseEntity.ok(new RespuestaEstandar("Insertados " + apiGeoMunicipioDto.totalResultsCount() + " municipios"));
    }
}
