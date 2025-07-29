package cu.edu.cujae.gestion.postgresql.geografia.core.controller;

import cu.edu.cujae.gestion.postgresql.geografia.core.dto.pais.PaisDtoEnviar;
import cu.edu.cujae.gestion.postgresql.geografia.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.geografia.core.model.Pais;
import cu.edu.cujae.gestion.postgresql.geografia.core.repository.PaisRepository;
import cu.edu.cujae.gestion.postgresql.geografia.core.service.PaisService;
import cu.edu.cujae.gestion.postgresql.geografia.core.serviceController.PaisControllerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para gestionar las operaciones relacionadas con los países.
 * Este controlador proporciona endpoints para listar todos los países y sus nombres.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/gestion/1/pais")
@Tag(name = "Controlador del país")
public class PaisController {
    private final PaisControllerService paisControllerService;

    @Autowired
    public PaisController(PaisControllerService paisControllerService) {
        this.paisControllerService = paisControllerService;
    }


    /**
     * Endpoint para obtener un listado de todos los países.
     * @return ResponseEntity con el estado HTTP y la lista de países.
     * Si ocurre un error al acceder a la lista, se devuelve un mensaje de error.
     * Este método devuelve una lista de objetos PaisDtoEnviar que contienen información detallada de cada país.
     */
    @GetMapping("/")
    @Operation(summary = "Listado de países",
            description = "Permite comprobar todo el listado de los paises")
    public ResponseEntity<RespuestaEstandar> obtenerPaises(HttpServletRequest request) {
        return paisControllerService.obtenerPaises();
    }

    /**
     * Endpoint para obtener un listado de los nombres de todos los países.
     * @return ResponseEntity con el estado HTTP y la lista de nombres de países.
     * Este método devuelve una lista de nombres de países como cadenas de texto.
     */
    @GetMapping("/nombre")
    @Operation(summary = "Listado de países",
            description = "Permite comprobar todo el listado de los nombres de los paises")
    public ResponseEntity<RespuestaEstandar> obtenerPaisesNombre(HttpServletRequest request) {
        return paisControllerService.obtenerPaisesNombre();
    }
}
