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
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class APIGeografica {

    @Value("${enlaces.geografia_pais}")
    private String urlInyectada;

    private static String url; // Esta será la que se usará en el método estático

    @PostConstruct
    private void init() {
        url = urlInyectada; // Se asigna el valor inyectado a la variable estática
    }

    /**
     * Obtener datos varios de un país
     * @param nombre
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static APIGeoDto obtenerDatosPaises(String nombre) throws IOException, InterruptedException {
        List<APIGeoDto> pais = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.
                newBuilder().
                header("Content-Type", "application/json").
                uri(URI.create(url+nombre)).
                build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        try{
            Type listType = new TypeToken<List<APIGeoDto>>() {}.getType();
            pais = new Gson().fromJson(response.body(), listType);
        }catch (Exception e){
            pais.add(new Gson().fromJson(response.body(), APIGeoDto.class));
        }
        return pais.getFirst();
    }
}
