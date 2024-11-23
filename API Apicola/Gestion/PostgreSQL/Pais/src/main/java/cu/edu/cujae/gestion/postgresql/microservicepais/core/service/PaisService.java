package cu.edu.cujae.gestion.postgresql.microservicepais.core.service;

import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.recibir.PaisDtoReci;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.sent.PaisDtoSent;

import java.util.List;

public interface PaisService {
    public void insertarPais(PaisDtoReci paisDtoReci);
    public void modificarPais(PaisDtoReci paisDtoReci, Long id);
    public void eliminarPais(int id);
    public PaisDtoSent buscarPais(int id);
    public PaisDtoSent buscarPaisPorNombre(String nombre);
    public List<PaisDtoSent> buscarPaises();

}
