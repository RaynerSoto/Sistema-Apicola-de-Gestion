package cu.edu.cujae.gestion.postgresql.geografia.core.controller;
import cu.edu.cujae.gestion.postgresql.geografia.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.geografia.core.service.ContinenteService;
import cu.edu.cujae.gestion.postgresql.geografia.core.serviceController.ContinenteControllerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para gestionar las operaciones relacionadas con los continentes.
 * Este controlador proporciona un endpoint para listar todos los continentes disponibles en el sistema.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/gestion/1/continente")
@Tag(name = "Controlador del contienente")
public class ContinenteController {
    private final ContinenteControllerService continenteControllerService;

    @Autowired
    public ContinenteController(ContinenteControllerService continenteService) {
        this.continenteControllerService = continenteService;
    }

    /**
     * Endpoint para obtener un listado de todos los continentes.
     * @return ResponseEntity con el estado HTTP y la lista de continentes.
     * Si ocurre un error al acceder a la lista, se devuelve un mensaje de error.
     */
    @GetMapping("/")
    @Operation(summary = "Listado de los continentes",description = "Permite listar a todos los continentes del sistema")
    public ResponseEntity<RespuestaEstandar> obtenerContinentes(HttpServletRequest request) {
        return continenteControllerService.obtenerContinentes();
    }
}
