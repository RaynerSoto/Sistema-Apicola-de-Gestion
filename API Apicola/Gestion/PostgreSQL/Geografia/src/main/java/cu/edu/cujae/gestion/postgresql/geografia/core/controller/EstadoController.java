package cu.edu.cujae.gestion.postgresql.geografia.core.controller;

import cu.edu.cujae.gestion.postgresql.geografia.core.dto.estado.EstadoDtoEnviar;
import cu.edu.cujae.gestion.postgresql.geografia.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.geografia.core.repository.EstadoRepository;
import cu.edu.cujae.gestion.postgresql.geografia.core.service.EstadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para gestionar las operaciones relacionadas con los estados de un país.
 * Este controlador proporciona un endpoint para listar todos los estados de un país específico.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/gestion/1/estado")
@Tag(name = "Controlador del estado")
public class EstadoController {
    private final EstadoService estadoService;
    private final EstadoRepository estadoRepository;

    @Autowired
    public EstadoController(EstadoService estadoService, EstadoRepository estadoRepository) {
        this.estadoService = estadoService;
        this.estadoRepository = estadoRepository;
    }

    /**
     * Endpoint para obtener un listado de todos los estados de un país específico.
     * @param nombrePais El nombre del país del cual se desea obtener los estados.
     * @return ResponseEntity con el estado HTTP y la lista de estados del país.
     * Si ocurre un error al acceder a la lista, se devuelve un mensaje de error.
     */
    @GetMapping("/{nombre}")
    @Operation(summary = "Listado de estados de un país",
            description = "Permite listar todos los estados de un pais con sus datos ocmpletos")
    public ResponseEntity<RespuestaEstandar> obtenerEstados(@PathVariable(value = "nombre") String nombrePais) {
        return ResponseEntity.ok(new RespuestaEstandar(estadoRepository.findAll().stream().filter(estado -> estado.getPais().getNombre().equals(nombrePais)).map(EstadoDtoEnviar::toEstado)));

    }
}
