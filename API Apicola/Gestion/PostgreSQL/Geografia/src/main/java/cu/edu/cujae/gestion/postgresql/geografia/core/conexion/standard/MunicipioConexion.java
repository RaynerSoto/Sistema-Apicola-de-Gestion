package cu.edu.cujae.gestion.postgresql.geografia.core.conexion.standard;

import com.google.gson.Gson;
import cu.edu.cujae.gestion.postgresql.estado.core.dto.enviar.PaisEstadoDto;
import cu.edu.cujae.gestion.postgresql.estado.core.dto.respuestaGenerica.RespuestaEstandar;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class MunicipioConexion {
    @Value("${enlaces.microservice-municipio}")
    private String urlInyectada;

    private static String url; // Esta será la que se usará en el método estático

    @PostConstruct
    private void init() {
        url = urlInyectada;
    }
    public static RespuestaEstandar municipioInsertar(PaisEstadoDto paisEstadoDto) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.
                newBuilder().
                header("Content-Type", "application/json").
                uri(URI.create(url)).
                POST(HttpRequest.BodyPublishers.ofString(paisEstadoDto.toString())).
                timeout(Duration.ofMinutes(80)).
                build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return new RespuestaEstandar(new Gson().fromJson(response.body(), RespuestaEstandar.class));
    }
}
