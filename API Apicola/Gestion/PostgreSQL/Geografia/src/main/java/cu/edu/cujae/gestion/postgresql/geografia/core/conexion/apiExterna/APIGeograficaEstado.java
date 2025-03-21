package cu.edu.cujae.gestion.postgresql.geografia.core.conexion.apiExterna;

import com.google.gson.Gson;
import cu.edu.cujae.gestion.postgresql.geografia.core.dto.apiGeografica.APIGeoEstadoDto;
import cu.edu.cujae.gestion.postgresql.geografia.core.exception.APIException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class APIGeograficaEstado {
    @Value("${enlaces.geografia_estado}")
    private String urlInyectada;

    private static String url; // Esta será la que se usará en el método estático

    @PostConstruct
    private void init() {
        url = urlInyectada;
    }

    public static APIGeoEstadoDto obtenerDatosEstado(String nombre) throws IOException, InterruptedException, APIException {
        try {
            APIGeoEstadoDto estados;
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.
                    newBuilder().
                    header("Content-Type", "application/json").
                    uri(URI.create(url.replace("%s",nombre))).
                    GET().
                    build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            estados = new Gson().fromJson(response.body(), APIGeoEstadoDto.class);
            return estados;
        }catch (Exception e){
            throw new APIException("La conexión con la API de GeoName ha sido defectuosa. Por favor contacte con la API o contacte al servicio técnico del sistema");
        }
    }
}
