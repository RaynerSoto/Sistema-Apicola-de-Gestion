package cu.edu.cujae.gestion.postgresql.geografia.core.conexion.apiExterna;

import com.google.gson.Gson;
import cu.edu.cujae.gestion.postgresql.geografia.core.dto.apiGeografica.APIGeoMunicipioDto;
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
public class APIGeograficaMunicipio {
    @Value("${enlaces.geografia_municipio}")
    private String urlInyectada;

    private static String url; // Esta será la que se usará en el método estático

    @PostConstruct
    private void init() {
        url = urlInyectada;
    }

    public static APIGeoMunicipioDto obtenerDatosMunicipio(String adminCode, String paisCode) throws IOException, InterruptedException, APIException {
        try {
            APIGeoMunicipioDto municipios;
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.
                    newBuilder().
                    header("Content-Type", "application/json").
                    uri(URI.create(String.format(url,adminCode,paisCode))).
                    GET().
                    build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            municipios = new Gson().fromJson(response.body(), APIGeoMunicipioDto.class);
            return municipios;
        }catch (Exception e){
            throw new APIException("La conexión con la API de GeoName ha sido defectuosa. Por favor contacte con la API o contacte al servicio técnico del sistema");
        }
    }
}
