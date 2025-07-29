package cu.edu.cujae.gestion.postgresql.usuarios.core.controller;

import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.SearchException;
import cu.edu.cujae.gestion.postgresql.usuarios.core.serviceController.SexoControllerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para manejar las operaciones relacionadas con los sexos.
 * Proporciona endpoints para listar sexos y sus nombres.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/v1/gestion/1/sexo")
@Tag(name = "Controlador de sexo")
public class SexoController {
    private final SexoControllerService sexoControllerService;

    @Autowired
    public SexoController(SexoControllerService sexoControllerService) {
        this.sexoControllerService = sexoControllerService;
    }

    @GetMapping("/")
    @Operation(summary = "Listado de sexos",description = "Permite listar los sexos")
    public ResponseEntity<RespuestaEstandar> listadoSexos(HttpServletRequest request) throws SearchException {
        return sexoControllerService.listadoSexos();
    }

    @GetMapping("/nombre")
    @Operation(summary = "Listado de nombre de los sexos",description = "Permite listar los nombres los sexos")
    public ResponseEntity<RespuestaEstandar> listadoSexosNombre(HttpServletRequest request) throws SearchException {
        return sexoControllerService.listadoSexosNombre();
    }
}
