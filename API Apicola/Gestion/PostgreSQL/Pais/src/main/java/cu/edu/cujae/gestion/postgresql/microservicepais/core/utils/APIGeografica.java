package cu.edu.cujae.gestion.postgresql.microservicepais.core.utils;

import com.google.gson.Gson;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.apiGeografica.APIGeoDto;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.traduccion.TraduccionRecibirPeticionDto;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;


public class APIGeografica {

    /**
     * Obtener datos varios de un país
     * @param nombre
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static APIGeoDto obtenerDatosPaises(String nombre) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.
                newBuilder().
                header("Content-Type", "application/json").
                uri(URI.create("https://restcountries.com/v3.1/name/"+nombre)).
                build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Type listType = new TypeToken<List<APIGeoDto>>() {}.getType();
        List<APIGeoDto> pais = new Gson().fromJson(response.body(), listType);
        return pais.getFirst();
    }

}
