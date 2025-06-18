package cu.edu.cujae.gestion.postgresql.usuarios.core.serviceController;

import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.usuario.UsuarioDtoRecibir;
import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.SearchException;

public interface UsuarioControllerService {
    RespuestaEstandar insertarUsuario(UsuarioDtoRecibir usuario) throws Exception;
    RespuestaEstandar modificarUsuario(UsuarioDtoRecibir usuario, Long id) throws Exception;
    RespuestaEstandar eliminarUsuario(Long id) throws Exception;
    RespuestaEstandar obtenerUsuario(Long id) throws SearchException;
    RespuestaEstandar obtenerUsuarios() throws SearchException;
}
