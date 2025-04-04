package cu.edu.cujae.gestion.postgresql.usuarios.core.conexion.apiExterna;

import com.google.gson.Gson;
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
public class APITraduction {

    @Value("${enlaces.traduccion}")
    private String urlInyectada;

    private static String url; // Esta será la que se usará en el método estático

    @PostConstruct
    private void init() {
        url = urlInyectada; // Se asigna el valor inyectado a la variable estática
    }

    /**
     * Realiza una solicitud HTTP a la API de traducción MyMemory para traducir el texto proporcionado desde español a inglés.
     * @param nombre
     * @return String
     * @throws IOException
     * @throws InterruptedException
     */
    public static String traduccionESEN(String nombre) throws IOException, InterruptedException, APIException {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.
                    newBuilder().
                    header("Content-Type", "application/json").
                    uri(URI.create(url + URLEncoder.encode(nombre, StandardCharsets.UTF_8) + "&langpair=es|en".replace("|", "%7C"))).
                    GET().build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            TraduccionRecibirPeticionDto pais = new Gson().fromJson(response.body(), TraduccionRecibirPeticionDto.class);
            return pais.responseData().translatedText();
        }
        catch (Exception e) {
            throw new APIException("La conexión con la API de mymemory para traducir ha sido defectuosa, contactar con el servicio técnico");
        }
    }

    /**
     * Realiza una solicitud HTTP a la API de traducción MyMemory para traducir el texto proporcionado desde español a inglés.
     * @param httpClient
     * @param nombre
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static String traduccionESEN(HttpClient httpClient, String nombre) throws IOException, InterruptedException, APIException {
        try {
            HttpRequest httpRequest = HttpRequest.
                    newBuilder().
                    header("Content-Type", "application/json").
                    uri(URI.create(url + URLEncoder.encode(nombre, StandardCharsets.UTF_8) + "&langpair=es|en".replace("|", "%7C"))).
                    GET().build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            TraduccionRecibirPeticionDto pais = new Gson().fromJson(response.body(), TraduccionRecibirPeticionDto.class);
            return pais.responseData().translatedText();
        }
        catch (Exception e) {
            throw new APIException("La conexión con la API de mymemory para traducir ha sido defectuosa, contactar con el servicio técnico");
        }
    }

    /**
     * Realiza una solicitud HTTP a la API de traducción MyMemory para traducir el texto proporcionado desde inglés a español.
     * @param nombre
     * @return String
     * @throws IOException
     * @throws InterruptedException
     */
    public static String traduccionENES(String nombre) throws IOException, InterruptedException, APIException {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.
                    newBuilder().
                    header("Content-Type", "application/json").
                    uri(URI.create(url + URLEncoder.encode(nombre, StandardCharsets.UTF_8) + "&langpair=en|es".replace("|", "%7C"))).
                    GET().build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            TraduccionRecibirPeticionDto pais = new Gson().fromJson(response.body(), TraduccionRecibirPeticionDto.class);
            return pais.responseData().translatedText();
        }
        catch (Exception e) {
            throw new APIException("La conexión con la API de mymemory para traducir ha sido defectuosa, contactar con el servicio técnico");
        }
    }

    /**
     * Realiza una solicitud HTTP a la API de traducción MyMemory para traducir el texto proporcionado desde inglés a español.
     * @param httpClient
     * @param nombre
     * @return String
     * @throws IOException
     * @throws InterruptedException
     */
    public static String traduccionENES(HttpClient httpClient, String nombre) throws IOException, InterruptedException, APIException {
        try {
            HttpRequest httpRequest = HttpRequest.
                    newBuilder().
                    header("Content-Type", "application/json").
                    uri(URI.create(url + URLEncoder.encode(nombre, StandardCharsets.UTF_8) + "&langpair=en|es".replace("|", "%7C"))).
                    GET().build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            TraduccionRecibirPeticionDto pais = new Gson().fromJson(response.body(), TraduccionRecibirPeticionDto.class);
            return pais.responseData().translatedText();
        }
        catch (Exception e) {
            throw new APIException("La conexión con la API de mymemory para traducir ha sido defectuosa, contactar con el servicio técnico");
        }

    }

    /**
     * Realiza una solicitud HTTP a la API de traducción MyMemory para traducir el texto proporcionado del idioma actual a otro idioma.
     * @param nombre
     * @param idiomaActual
     * @param idiomaATraduccion
     * @return String
     * @throws IOException
     * @throws InterruptedException
     */
    public static String traduccion(String nombre, String idiomaActual, String idiomaATraduccion) throws IOException, InterruptedException, APIException {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.
                    newBuilder().
                    header("Content-Type", "application/json").
                    uri(URI.create(url + URLEncoder.encode(nombre, StandardCharsets.UTF_8) + "&langpair="+idiomaActual+"|".replace("|", "%7C")+idiomaATraduccion)).
                    GET().build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            TraduccionRecibirPeticionDto pais = new Gson().fromJson(response.body(), TraduccionRecibirPeticionDto.class);
            return pais.responseData().translatedText();
        } catch (Exception e) {
            throw new APIException("La conexión con la API de mymemory para traducir ha sido defectuosa, contactar con el servicio técnico");
        }
    }

    /**
     * Realiza una solicitud HTTP a la API de traducción MyMemory para traducir el texto proporcionado del idioma actual a otro idioma.
     * @param httpClient
     * @param nombre
     * @param idiomaActual
     * @param idiomaATraduccion
     * @return String
     * @throws IOException
     * @throws InterruptedException
     */
    public static String traduccion(HttpClient httpClient, String nombre, String idiomaActual, String idiomaATraduccion) throws IOException, InterruptedException, APIException {
        try{
            HttpRequest httpRequest = HttpRequest.
                    newBuilder().
                    header("Content-Type", "application/json").
                    uri(URI.create(url + URLEncoder.encode(nombre, StandardCharsets.UTF_8) + "&langpair="+idiomaActual.toLowerCase()+"|".replace("|", "%7C")+idiomaATraduccion.toLowerCase())).
                    GET().build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            TraduccionRecibirPeticionDto pais = new Gson().fromJson(response.body(), TraduccionRecibirPeticionDto.class);
            return pais.responseData().translatedText();
        } catch (Exception e) {
            throw new APIException("La conexión con la API de mymemory para traducir ha sido defectuosa, contactar con el servicio técnico");
        }
    }
}
