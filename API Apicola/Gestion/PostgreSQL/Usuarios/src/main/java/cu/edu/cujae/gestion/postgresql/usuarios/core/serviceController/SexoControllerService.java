package cu.edu.cujae.gestion.postgresql.usuarios.core.serviceController;

import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.SearchException;
import org.springframework.http.ResponseEntity;

public interface SexoControllerService {
    ResponseEntity<RespuestaEstandar> listadoSexos() throws SearchException;
    ResponseEntity<RespuestaEstandar> listadoSexosNombre() throws SearchException;
    ResponseEntity<RespuestaEstandar> obtenerSexo(Long id) throws SearchException;
    ResponseEntity<RespuestaEstandar> obtenerSexoNombre(String nombre) throws SearchException;
}
