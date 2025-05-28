package cu.edu.cujae.gestion.postgresql.geografia.core.service;

import cu.edu.cujae.gestion.postgresql.geografia.core.model.Estado;

/**
 * Servicio para gestionar las operaciones CRUD de la entidad Estado.
 * Este servicio proporciona un método para insertar un estado en la base de datos.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
public interface EstadoService {
    void insertarEstado(Estado estado);
}
