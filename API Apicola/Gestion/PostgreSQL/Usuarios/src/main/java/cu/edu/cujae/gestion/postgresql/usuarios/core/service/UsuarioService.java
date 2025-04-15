package cu.edu.cujae.gestion.postgresql.usuarios.core.service;

import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Usuario;

public interface UsuarioService {
    void insertarUsuario(Usuario usuario);
    void actualizarUsuario(Usuario usuario);
    void eliminarUsuario(Long idUsuario);
    Usuario getUsuario(Long idUsuario);
}
