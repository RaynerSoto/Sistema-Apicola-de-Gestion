package cu.edu.cujae.gestion.postgresql.geografia.core.serviceController;

import cu.edu.cujae.gestion.postgresql.geografia.core.dto.paisEstado.PaisEstadoDtoComprobar;
import cu.edu.cujae.gestion.postgresql.geografia.core.dto.respuestaGenerica.RespuestaEstandar;
import org.springframework.http.ResponseEntity;

/**
 * Interface para GeografiaControllerService, que define los métodos para manejar operaciones relacionadas con la geografía.
 * @author Rayner Alejandro Soto Martínez
 * @version 0.1.0
 */
public interface GeografiaControllerService {
    ResponseEntity<RespuestaEstandar> insertarDatosGeograficos(String nombre) throws Exception;

    @Deprecated
    ResponseEntity<RespuestaEstandar> comprobarPaisEstado(PaisEstadoDtoComprobar paisEstado);

    ResponseEntity<RespuestaEstandar> obtenerPaisEstadoID(PaisEstadoDtoComprobar paisEstado) throws Exception;

    ResponseEntity<RespuestaEstandar> obtenerPaisEstadoNombre(Long idPais, Long idEstado) throws Exception;
}
