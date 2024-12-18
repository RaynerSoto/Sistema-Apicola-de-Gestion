package cu.edu.cujae.gestion.postgresql.microservicepais.core.service;

import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.sent.PaisDtoSent;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.model.Pais;

import java.util.List;

public interface PaisService {
    public void insertarPais(Pais pais);
    public void modificarPais(Pais pais) throws Exception;
    public void eliminarPais(Long id) throws Exception;
    public PaisDtoSent buscarPais(Long id);
    public PaisDtoSent buscarPaisPorNombre(String nombre);
    public List<PaisDtoSent> buscarPaises();
}
