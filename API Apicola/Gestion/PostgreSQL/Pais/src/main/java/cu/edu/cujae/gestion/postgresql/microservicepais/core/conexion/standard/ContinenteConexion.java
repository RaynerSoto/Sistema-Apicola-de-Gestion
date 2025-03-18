package cu.edu.cujae.gestion.postgresql.microservicepais.core.conexion.standard;

import com.google.gson.Gson;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.traduccion.TraduccionRecibirPeticionDto;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ContinenteConexion {

    @Value("${enlaces.microservice-continente}")
    private String urlInyectada;

    private static String url; // Esta será la que se usará en el método estático

    @PostConstruct
    private void init() {
        url = urlInyectada;
    }
    public static RespuestaEstandar getContinente(String nombre) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.
                newBuilder().
                header("Content-Type", "application/json").
                uri(URI.create(url+nombre)).
                timeout(Duration.ofSeconds(80)).
                build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return new RespuestaEstandar(new Gson().fromJson(response.body(), RespuestaEstandar.class));
    }

}
