package cu.edu.cujae.gestion.postgresql.usuarios.core.service;

import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Rol;

import java.util.List;

/**
 * Interfaces para RolService para poder manejar los roles de los usuarios.
 * Los roles son utilizados para definir los permisos y accesos de los usuarios dentro del sistema.
 * Esta interfaz proporciona un método para listar todos los roles disponibles.
 * @see cu.edu.cujae.gestion.postgresql.usuarios.core.model.Rol
 * Esta interfaz es parte del paquete de servicios del sistema de gestión de usuarios.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
public interface RolService {
    public List<String> listadoRoles();
}
