package cu.edu.cujae.gestion.postgresql.usuarios.core.service;

import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.SearchException;
import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Usuario;

public interface UsuarioService {
    void insertarUsuario(Usuario usuario);
    void actualizarUsuario(Usuario usuario) throws SearchException;
    void eliminarUsuario(Long idUsuario) throws SearchException;
    Usuario getUsuario(Long idUsuario) throws SearchException;
}
