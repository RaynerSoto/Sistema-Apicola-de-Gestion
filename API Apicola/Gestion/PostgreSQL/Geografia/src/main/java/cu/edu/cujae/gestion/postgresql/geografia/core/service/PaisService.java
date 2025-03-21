package cu.edu.cujae.gestion.postgresql.geografia.core.service;

import cu.edu.cujae.gestion.postgresql.geografia.core.model.Pais;

import java.util.List;

public interface PaisService {
    public void insertarPais(Pais pais);
    public void modificarPais(Pais pais) throws Exception;
    public void actualizarPais(Pais pais) throws Exception;
    public void eliminarPais(Long id) throws Exception;
}
