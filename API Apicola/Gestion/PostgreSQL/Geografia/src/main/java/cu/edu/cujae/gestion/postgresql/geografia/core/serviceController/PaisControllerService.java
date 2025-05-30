package cu.edu.cujae.gestion.postgresql.geografia.core.serviceController;

import cu.edu.cujae.gestion.postgresql.geografia.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.geografia.core.exception.SearchException;
import org.springframework.http.ResponseEntity;

/**
 * Interface para PaisControllerService, que define los métodos para manejar operaciones relacionadas con países.
 * @author Rayner Alejandro Soto Martínez
 * @version 0.1.0
 */
public interface PaisControllerService {
    ResponseEntity<RespuestaEstandar> obtenerPaises();
    ResponseEntity<RespuestaEstandar> obtenerPaisesNombre();
}
