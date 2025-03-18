package cu.edu.cujae.gestion.postgresql.estado.core.controller;

import cu.edu.cujae.gestion.postgresql.estado.core.dto.apiGeografica.APIGeoEstadoDto;
import cu.edu.cujae.gestion.postgresql.estado.core.dto.apiGeografica.GeonamesDto;
import cu.edu.cujae.gestion.postgresql.estado.core.dto.enviar.PaisEnviadoDto;
import cu.edu.cujae.gestion.postgresql.estado.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.estado.core.exception.APIException;
import cu.edu.cujae.gestion.postgresql.estado.core.model.Estado;
import cu.edu.cujae.gestion.postgresql.estado.core.service.EstadoService;
import cu.edu.cujae.gestion.postgresql.estado.core.conexion.apiExterna.APIGeograficaEstado;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/gestion/1/estado")
@Tag(name = "Controlador del Estado/Provincia")
public class EstadoController {
    private final EstadoService estadoService;

    @Autowired
    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @PostMapping("/")
    public ResponseEntity<RespuestaEstandar> insertarEstados(@RequestBody PaisEnviadoDto paisEnviadoDto) throws APIException, IOException, InterruptedException {
        APIGeoEstadoDto apiGeoEstadoDto = APIGeograficaEstado.obtenerDatosEstado(paisEnviadoDto.codigo());
        List<GeonamesDto> estados = apiGeoEstadoDto.geonames()
                .stream()
                .map(estado -> {
                    try {
                        estadoService.insertarEstado(new Estado(paisEnviadoDto,estado));
                        return null;
                    }catch (Exception e){
                        return estado;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
        if (estados.isEmpty())
            return ResponseEntity.ok(new RespuestaEstandar(String.format("Cargado datos los estados %s del país",apiGeoEstadoDto.totalResultsCount())));
        else
            return ResponseEntity.ok(new RespuestaEstandar(String.format("Solamente se han podido cargar los %s estados del país",estados.size())));
    }
}
