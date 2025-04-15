package cu.edu.cujae.gestion.postgresql.usuarios.core.conexion.microservicios;

import com.google.gson.Gson;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.paisEstado.PaisEstadoComprobar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.traduccion.TraduccionRecibirPeticionDto;
import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.APIException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Component
public class APIGeografia {

    @Value("${enlaces.geografia_microservicio_geo}")
    private String urlInyectada;

    private static String url; // Esta será la que se usará en el método estático

    @PostConstruct
    private void init() {
        url = urlInyectada; // Se asigna el valor inyectado a la variable estática
    }

    public RespuestaEstandar comprobarPaisEstado(PaisEstadoComprobar paisEstado) throws IOException, InterruptedException, APIException {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.
                    newBuilder().
                    header("Content-Type", "application/json").
                    uri(URI.create(url + "/PaisEstado")).
                    POST(HttpRequest.BodyPublishers.ofString(paisEstado.toJSON())).
                    build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), RespuestaEstandar.class);
        }catch (Exception e){
            throw new APIException("La conexión con la API de Geografia para la comprobación de los datos ha sido defectuosa, contactar con el servicio técnico");
        }
    }

}
