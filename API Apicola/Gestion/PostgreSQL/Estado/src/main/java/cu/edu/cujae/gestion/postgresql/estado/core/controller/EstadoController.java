package cu.edu.cujae.gestion.postgresql.estado.core.controller;

import cu.edu.cujae.gestion.postgresql.estado.core.dto.apiGeografica.APIGeoEstadoDto;
import cu.edu.cujae.gestion.postgresql.estado.core.dto.enviar.PaisEnviadoDto;
import cu.edu.cujae.gestion.postgresql.estado.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.estado.core.model.Estado;
import cu.edu.cujae.gestion.postgresql.estado.core.service.EstadoService;
import cu.edu.cujae.gestion.postgresql.estado.core.utils.APIGeograficaEstado;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<RespuestaEstandar> insertarEstados(@RequestBody PaisEnviadoDto paisEnviadoDto){
        try {
            APIGeoEstadoDto apiGeoEstadoDto = APIGeograficaEstado.obtenerDatosEstado(paisEnviadoDto.codigo());
            apiGeoEstadoDto.geonames()
                    .stream()
                    .map(estado -> {
                        estadoService.insertarEstado(new Estado(paisEnviadoDto,estado));
                        return null;
                    });
            return ResponseEntity.ok(new RespuestaEstandar("Cargado datos de los estados del país"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new RespuestaEstandar(e.getMessage()));
        }
    }
}
