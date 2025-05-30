package cu.edu.cujae.gestion.postgresql.geografia.core.serviceController;

import cu.edu.cujae.gestion.postgresql.geografia.core.dto.respuestaGenerica.RespuestaEstandar;
import org.springframework.http.ResponseEntity;

/**
 * Interfaz para ContinenteControllerService, que define los métodos para manejar operaciones relacionadas con continentes.
 * @author Rayner Alejandro Soto Martínez
 * @version 0.1.0
 */
public interface ContinenteControllerService {
    ResponseEntity<RespuestaEstandar> obtenerContinentes();
}
