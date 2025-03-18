package cu.edu.cujae.gestion.postgresql.microservicepais.core.service.implement;
import com.google.gson.Gson;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.continente.ContinenteDto;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.conexion.openfeign.ContinenteOpenFeign;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.service.ContinentService;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.utils.ConversionObjetos;
import feign.FeignException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ContinentServiceImplement implements ContinentService {
    private final ContinenteOpenFeign continenteOpenFeign;

    @Autowired
    public ContinentServiceImplement(ContinenteOpenFeign continentService) {
        this.continenteOpenFeign = continentService;
    }

    @Override
    public String obtenerContinente(String continent) throws Exception {
        RespuestaEstandar respuesta;
        try {
            if (continent.isBlank())
                throw new IllegalArgumentException("No se puede buscar un continente con dichos valores");
            respuesta = continenteOpenFeign.getContinente(continent);
            return ((ContinenteDto)ConversionObjetos.convertirHashMapaObject((HashMap<String, Object>) respuesta.getRespuesta(),ContinenteDto.class)).getNombre();
        } catch (FeignException e) {
            respuesta = new Gson().fromJson(e.contentUTF8(), RespuestaEstandar.class);
            throw new EntityNotFoundException((String) respuesta.getRespuesta());
        }
    }
}
