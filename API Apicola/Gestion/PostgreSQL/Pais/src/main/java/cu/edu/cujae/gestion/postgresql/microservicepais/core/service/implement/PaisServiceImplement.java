package cu.edu.cujae.gestion.postgresql.microservicepais.core.service.implement;

import cu.edu.cujae.gestion.postgresql.microservicepais.core.repository.PaisRepository;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaisServiceImplement implements PaisService {
    private PaisRepository paisRepository;

    @Autowired
    public PaisServiceImplement(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }


}
