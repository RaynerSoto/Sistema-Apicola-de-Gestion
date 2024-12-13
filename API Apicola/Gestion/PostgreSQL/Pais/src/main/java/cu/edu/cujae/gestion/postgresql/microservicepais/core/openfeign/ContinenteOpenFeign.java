package cu.edu.cujae.gestion.postgresql.microservicepais.core.openfeign;

import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.continente.RespuestaContinenteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-continente",url = "http://localhost:8085/api/v1/gestion/1/continente")
public interface ContinenteOpenFeign {
    @GetMapping("/{nombre}")
    public RespuestaContinenteDto getContinente(@PathVariable(value = "nombre") String nombre);
}
