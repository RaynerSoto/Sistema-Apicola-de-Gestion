package cu.edu.cujae.gestion.postgresql.geografia.core.service;

import cu.edu.cujae.gestion.postgresql.geografia.core.model.Pais;

import java.util.List;

/**
 * Servicio para gestionar las operaciones CRUD de la entidad Pais.
 * Este servicio proporciona métodos para insertar, modificar, actualizar y eliminar países.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
public interface PaisService {
    public void insertarPais(Pais pais);
    public void modificarPais(Pais pais) throws Exception;
    public void actualizarPais(Pais pais) throws Exception;
    public void eliminarPais(Long id) throws Exception;
}
