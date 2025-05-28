package cu.edu.cujae.gestion.postgresql.usuarios.core.service;

import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.SearchException;
import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Usuario;

/**
 * Interfaz que define los servicios relacionados con la gestión de usuarios.
 * Contiene métodos para insertar, actualizar, eliminar y obtener usuarios.
 * Esta interfaz es parte del paquete de servicios del sistema de gestión de usuarios.
 * Los servicios proporcionados permiten la manipulación de los datos de los usuarios
 * dentro del sistema, asegurando que las operaciones se realicen de manera consistente y segura.
 * @see cu.edu.cujae.gestion.postgresql.usuarios.core.model.Usuario
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
public interface UsuarioService {
    void insertarUsuario(Usuario usuario);
    void actualizarUsuario(Usuario usuario) throws SearchException;
    void eliminarUsuario(Long idUsuario) throws SearchException;
    Usuario getUsuario(Long idUsuario) throws SearchException;
}
