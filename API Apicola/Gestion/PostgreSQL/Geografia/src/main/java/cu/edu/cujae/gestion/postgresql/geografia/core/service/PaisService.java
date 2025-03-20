package cu.edu.cujae.gestion.postgresql.geografia.core.service;

import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.pais.enviar.PaisDtoSent;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.exception.SearchException;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.model.Pais;

import java.util.List;

public interface PaisService {
    public void insertarPais(Pais pais);
    public void modificarPais(Pais pais) throws Exception;
    public void actualizarPais(Pais pais) throws Exception;
    public void eliminarPais(Long id) throws Exception;
    public PaisDtoSent buscarPais(Long id);
    public PaisDtoSent buscarPaisPorNombre(String nombre) throws SearchException;
    public List<PaisDtoSent> buscarPaises();
}
