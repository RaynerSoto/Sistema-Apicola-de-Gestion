package cu.edu.cujae.gestion.postgresql.geografia.core.serviceController;

import cu.edu.cujae.gestion.postgresql.geografia.core.dto.respuestaGenerica.RespuestaEstandar;
import org.springframework.http.ResponseEntity;

/**
 * Interface para EstadoControllerService, que define los métodos para manejar operaciones relacionadas con estados.
 * @author Rayner Alejandro Soto Martínez
 * @version 0.1.0
 */
public interface EstadoControllerService {
    ResponseEntity<RespuestaEstandar> obtenerEstados(String nombrePais);
}
