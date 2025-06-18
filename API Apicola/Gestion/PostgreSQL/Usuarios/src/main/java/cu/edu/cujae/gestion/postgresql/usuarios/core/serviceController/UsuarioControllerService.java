package cu.edu.cujae.gestion.postgresql.usuarios.core.serviceController;

import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.usuario.UsuarioDtoRecibir;
import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.SearchException;
import org.springframework.http.ResponseEntity;

public interface UsuarioControllerService {
    ResponseEntity<RespuestaEstandar> insertarUsuario(UsuarioDtoRecibir usuario) throws Exception;
    ResponseEntity<RespuestaEstandar> modificarUsuario(UsuarioDtoRecibir usuario, Long id) throws Exception;
    ResponseEntity<RespuestaEstandar> eliminarUsuario(Long id) throws Exception;
    ResponseEntity<RespuestaEstandar> obtenerUsuario(Long id) throws SearchException;
    ResponseEntity<RespuestaEstandar> obtenerUsuarios() throws SearchException;
}
