package cu.edu.cujae.gestion.postgresql.geografia.core.conexion.apiExterna;

import com.google.gson.Gson;
import cu.edu.cujae.gestion.postgresql.geografia.core.dto.traduccion.TraduccionRecibirPeticionDto;
import cu.edu.cujae.gestion.postgresql.geografia.core.exception.APIException;
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

/**
 * Clase que se encarga de realizar la conexión con la API externa de MyMemory para traducir textos entre español e inglés.
 * Esta clase utiliza el cliente HTTP de Java para enviar solicitudes GET a la API y recibir respuestas en formato JSON.
 * La URL de la API se inyecta desde el archivo de propiedades de la aplicación.
 * La clase maneja excepciones relacionadas con la conexión a la API y el procesamiento de la respuesta.
 * La información obtenida se convierte a un objeto de tipo TraduccionRecibirPeticionDto utilizando la biblioteca Gson.
 * Esta clase es un componente de Spring, lo que permite su inyección en otras partes de la aplicación.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
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
     * @param nombre El texto a traducir.
     * @return String
     * @throws IOException Si ocurre un error de entrada/salida durante la conexión a la API.
     * @throws InterruptedException Si la operación es interrumpida durante la solicitud HTTP.
     * @throws APIException Si ocurre un error al procesar la respuesta de la API o al establecer la conexión.
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
     * @param httpClient El cliente HTTP utilizado para realizar la solicitud.
     * @param nombre El texto a traducir.
     * @return String
     * @throws IOException Si ocurre un error de entrada/salida durante la conexión a la API.
     * @throws InterruptedException Si la operación es interrumpida durante la solicitud HTTP.
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
     * @param nombre El texto a traducir.
     * @return String
     * @throws IOException Si ocurre un error de entrada/salida durante la conexión a la API.
     * @throws InterruptedException Si la operación es interrumpida durante la solicitud HTTP.
     * @throws APIException Si ocurre un error al procesar la respuesta de la API o al establecer la conexión.
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
     * @param httpClient El cliente HTTP utilizado para realizar la solicitud.
     * @param nombre El texto a traducir.
     * @return String
     * @throws IOException Si ocurre un error de entrada/salida durante la conexión a la API.
     * @throws InterruptedException Si la operación es interrumpida durante la solicitud HTTP.
     * @throws APIException Si ocurre un error al procesar la respuesta de la API o al establecer la conexión.
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
     * @param nombre El texto a traducir.
     * @param idiomaActual El código del idioma actual (por ejemplo, "es" para español).
     * @param idiomaATraduccion El código del idioma al que se desea traducir (por ejemplo, "en" para inglés).
     * @return String
     * @throws IOException Si ocurre un error de entrada/salida durante la conexión a la API.
     * @throws InterruptedException Si la operación es interrumpida durante la solicitud HTTP.
     * @throws APIException Si ocurre un error al procesar la respuesta de la API o al establecer la conexión.
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
     * @param httpClient El cliente HTTP utilizado para realizar la solicitud.
     * @param nombre El texto a traducir.
     * @param idiomaActual El código del idioma actual (por ejemplo, "es" para español).
     * @param idiomaATraduccion El código del idioma al que se desea traducir (por ejemplo, "en" para inglés).
     * @return String
     * @throws IOException Si ocurre un error de entrada/salida durante la conexión a la API.
     * @throws InterruptedException Si la operación es interrumpida durante la solicitud HTTP.
     * @throws APIException Si ocurre un error al procesar la respuesta de la API o al establecer la conexión.
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
