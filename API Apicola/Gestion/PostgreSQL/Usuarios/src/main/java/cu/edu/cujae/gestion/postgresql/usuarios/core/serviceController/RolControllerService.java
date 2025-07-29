package cu.edu.cujae.gestion.postgresql.usuarios.core.serviceController;

import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.SearchException;
import org.springframework.http.ResponseEntity;

public interface RolControllerService {
    ResponseEntity<RespuestaEstandar> listadoRoles() throws SearchException;
    ResponseEntity<RespuestaEstandar> listadoRolesNombre() throws SearchException;
    ResponseEntity<RespuestaEstandar> obtenerRolNombre(String nombre) throws SearchException;
    ResponseEntity<RespuestaEstandar> obtenerRolID(Long id) throws SearchException;
}
