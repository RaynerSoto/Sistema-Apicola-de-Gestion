package cu.edu.cujae.gestion.postgresql.geografia.core.service;

import cu.edu.cujae.gestion.postgresql.geografia.core.model.Municipio;

/**
 * Servicio para gestionar las operaciones CRUD de la entidad Municipio.
 * Este servicio proporciona un método para insertar un municipio en la base de datos.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
@Deprecated
public interface MunicipioService {
    public void insertarMunicipio(Municipio municipio);
}
