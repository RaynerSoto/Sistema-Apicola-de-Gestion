package cu.edu.cujae.gestion.postgresql.geografia.core.conexion.openfeign;

import cu.edu.cujae.gestion.postgresql.estado.core.dto.respuestaGenerica.RespuestaEstandar;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "microservice-pais",url = "http://localhost:8086/api/v1/gestion/1/pais")
public interface PaisOpenFeign {
    @GetMapping("/{id}")
    RespuestaEstandar buscarPais(Long id);

}
