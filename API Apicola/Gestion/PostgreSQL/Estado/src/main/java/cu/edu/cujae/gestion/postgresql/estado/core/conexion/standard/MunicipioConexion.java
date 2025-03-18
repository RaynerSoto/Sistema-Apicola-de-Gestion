package cu.edu.cujae.gestion.postgresql.estado.core.conexion.standard;

import cu.edu.cujae.gestion.postgresql.estado.core.dto.respuestaGenerica.RespuestaEstandar;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

public class MunicipioConexion {
    @Value("${enlaces.microservice-municipio}")
    private String urlInyectada;

    private static String url; // Esta será la que se usará en el método estático

    @PostConstruct
    private void init() {
        url = urlInyectada;
    }
    public static RespuestaEstandar municipioInsertar(){

    }
}
