package cu.edu.cujae.gestion.postgresql.microservicepais.core.service.implement;

import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.continente.RespuestaContinenteDto;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.openfeign.ContinenteOpenFeign;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.service.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.rmi.ServerException;

@Service
public class ContinentServiceImplement implements ContinentService {
    private final ContinenteOpenFeign continenteOpenFeign;

    @Autowired
    public ContinentServiceImplement(ContinenteOpenFeign continentService) {
        this.continenteOpenFeign = continentService;
    }

    @Override
    public String obtenerContinente(String continent) throws Exception {
        if (continent.isBlank())
            throw new IllegalArgumentException("No se puede buscar un continente con dichos valores");
        RespuestaContinenteDto respuesta = continenteOpenFeign.getContinente(continent);
        if (!respuesta.isIscorrectoRespuesta())
            throw new ServerException(respuesta.getRespuesta());
        return respuesta.getContinenteDto().getNombre();
    }
}
