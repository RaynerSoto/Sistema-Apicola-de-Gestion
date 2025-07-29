package cu.edu.cujae.gestion.postgresql.usuarios.core.service;

import java.util.List;

/**
 * Interfaces para SexoService para poder manejar los sexos de los usuarios.
 * Los sexos son utilizados para definir características demográficas de los usuarios dentro del sistema.
 * Esta interfaz proporciona un método para listar todos los sexos disponibles.
 * @see cu.edu.cujae.gestion.postgresql.usuarios.core.model.Sexo
 * Esta interfaz es parte del paquete de servicios del sistema de gestión de usuarios.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
public interface SexoService {
    public List<String> listadoSexos();
}
