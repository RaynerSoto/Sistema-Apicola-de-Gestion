package cu.edu.cujae.gestion.postgresql.microservicepais.core.openfeign;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.pais.enviar.PaisEnviadoDto;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.respuestaGenerica.RespuestaEstandar;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservice-estado",url = "http://localhost:8087/api/v1/gestion/1/estado")
public interface EstadoOpenFeing {
    @PostMapping("/")
    RespuestaEstandar insertarEstado(@RequestBody PaisEnviadoDto paisEnviadoDto);
}
