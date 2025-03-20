package cu.edu.cujae.gestion.postgresql.geografia.core.conexion.apiExterna;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.apiGeografica.APIGeoPaisDto;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.exception.APIException;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.exception.SearchException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class APIGeograficaPais {

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
    public static APIGeoPaisDto obtenerDatosPaises(String nombre) throws IOException, InterruptedException, APIException,IOException, InterruptedException {
        try {
            List<APIGeoPaisDto> paises = new ArrayList<>();
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.
                    newBuilder().
                    header("Content-Type", "application/json").
                    uri(URI.create(url+nombre.toUpperCase())).
                    build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            try{
                Type listType = new TypeToken<List<APIGeoPaisDto>>() {}.getType();
                paises = new Gson().fromJson(response.body(), listType);
            }catch (Exception e){
                paises.add(new Gson().fromJson(response.body(), APIGeoPaisDto.class));
            }
           try {
               APIGeoPaisDto pais = paises.stream()
                       .map(apiGeoPaisDto -> {
                           try {
                               return apiGeoPaisDto.toEspañol();
                           } catch (IOException e) {
                               throw new RuntimeException(e);
                           } catch (InterruptedException e) {
                               throw new RuntimeException(e);
                           } catch (APIException e) {
                               throw new RuntimeException(e);
                           }
                       }).filter(apiGeoPaisDto -> apiGeoPaisDto.name().common().equals(nombre)).collect(Collectors.toList()).getFirst();
               return pais;
           } catch (Exception e) {
               throw new SearchException("No se encontró el país específico");
           }
        } catch (Exception e) {
            throw new APIException("La conexión con la API de RestCountries a sido defectuosa. Por favor contacte con la API o contacte al servicio técnico del sistema");
        }
    }
}
