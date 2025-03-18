package cu.edu.cujae.gestion.postgresql.microservicepais.core.conexion.standard;

import com.google.gson.Gson;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.pais.enviar.PaisEnviadoDto;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.respuestaGenerica.RespuestaEstandar;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class EstadoConexion {
    @Value("${enlaces.microservice-estado}")
    private String urlInyectada;

    private static String url; // Esta será la que se usará en el método estático

    @PostConstruct
    private void init() {
        url = urlInyectada;
    }

    public static RespuestaEstandar insertarEstado(PaisEnviadoDto paisEnviadoDto) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.
                newBuilder().
                header("Content-Type", "application/json").
                uri(URI.create(url)).
                POST(HttpRequest.BodyPublishers.ofString(paisEnviadoDto.toString())).
                timeout(Duration.ofSeconds(80)).
                build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return new RespuestaEstandar(new Gson().fromJson(response.body(), RespuestaEstandar.class));
    }
}
