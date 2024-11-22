package cu.edu.cujae.gestion.postgresql.microservicepais.core.service;

import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.recibir.PaisDto;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.sent.PaisDtoSent;

import java.util.List;

public interface PaisService {
    public void insertarPais(PaisDto paisDto);
    public void modificarPais(PaisDto paisDto, Long id);
    public void eliminarPais(int id);
    public PaisDtoSent buscarPais(int id);
    public List<PaisDtoSent> buscarPaises();

}
