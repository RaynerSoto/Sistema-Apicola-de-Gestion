package cu.edu.cujae.gestion.postgresql.microservicepais.core.service.implement;

import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.recibir.PaisDtoReci;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.sent.PaisDtoSent;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.repository.PaisRepository;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisServiceImplement implements PaisService {
    private PaisRepository paisRepository;

    @Autowired
    public PaisServiceImplement(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }


    @Override
    public void insertarPais(PaisDtoReci paisDtoReci) {

    }

    @Override
    public void modificarPais(PaisDtoReci paisDtoReci, Long id) {

    }

    @Override
    public void eliminarPais(int id) {

    }

    @Override
    public PaisDtoSent buscarPais(int id) {
        return null;
    }

    @Override
    public PaisDtoSent buscarPaisPorNombre(String nombre) {
        return null;
    }

    @Override
    public List<PaisDtoSent> buscarPaises() {
        return List.of();
    }
}
