package cu.edu.cujae.gestion.postgresql.usuarios.core.serviceController;

import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.SearchException;
import org.springframework.http.ResponseEntity;

public interface RolControllerService {
    RespuestaEstandar listadoRoles() throws SearchException;
    RespuestaEstandar listadoRolesNombre() throws SearchException;
}
