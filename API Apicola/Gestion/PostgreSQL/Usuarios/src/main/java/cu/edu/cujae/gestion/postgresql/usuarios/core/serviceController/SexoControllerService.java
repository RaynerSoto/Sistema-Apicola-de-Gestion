package cu.edu.cujae.gestion.postgresql.usuarios.core.serviceController;

import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.SearchException;

public interface SexoControllerService {
    RespuestaEstandar listadoSexos() throws SearchException;
    RespuestaEstandar listadoSexosNombre() throws SearchException;
}
