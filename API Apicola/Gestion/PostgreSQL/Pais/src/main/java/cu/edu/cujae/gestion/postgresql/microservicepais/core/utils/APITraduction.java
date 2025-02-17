package cu.edu.cujae.gestion.postgresql.microservicepais.core.utils;

import com.google.gson.Gson;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.traduccion.TraduccionRecibirPeticionDto;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class APITraduction {

    /**
     * Realiza una solicitud HTTP a la API de traducción MyMemory para traducir el texto proporcionado desde español a inglés.
     * @param nombre
     * @return String
     * @throws IOException
     * @throws InterruptedException
     */
    public static String traduccionESEN(String nombre) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.
                newBuilder().
                header("Content-Type", "application/json").
                uri(URI.create("https://api.mymemory.translated.net/get?q="+ URLEncoder.encode(nombre, StandardCharsets.UTF_8) + "&langpair=es|en".replace("|", "%7C"))).
                GET().build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        TraduccionRecibirPeticionDto pais = new Gson().fromJson(response.body(), TraduccionRecibirPeticionDto.class);
        return pais.responseData().translatedText();
    }

    /**
     * Realiza una solicitud HTTP a la API de traducción MyMemory para traducir el texto proporcionado desde español a inglés.
     * @param httpClient
     * @param nombre
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static String traduccionESEN(HttpClient httpClient, String nombre) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.
                newBuilder().
                header("Content-Type", "application/json").
                uri(URI.create("https://api.mymemory.translated.net/get?q="+ URLEncoder.encode(nombre, StandardCharsets.UTF_8) + "&langpair=es|en".replace("|", "%7C"))).
                GET().build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        TraduccionRecibirPeticionDto pais = new Gson().fromJson(response.body(), TraduccionRecibirPeticionDto.class);
        return pais.responseData().translatedText();
    }

    /**
     * Realiza una solicitud HTTP a la API de traducción MyMemory para traducir el texto proporcionado desde inglés a español.
     * @param nombre
     * @return String
     * @throws IOException
     * @throws InterruptedException
     */
    public static String traduccionENES(String nombre) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.
                newBuilder().
                header("Content-Type", "application/json").
                uri(URI.create("https://api.mymemory.translated.net/get?q="+ URLEncoder.encode(nombre, StandardCharsets.UTF_8) + "&langpair=en|es".replace("|", "%7C"))).
                GET().build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        TraduccionRecibirPeticionDto pais = new Gson().fromJson(response.body(), TraduccionRecibirPeticionDto.class);
        return pais.responseData().translatedText();
    }

    /**
     * Realiza una solicitud HTTP a la API de traducción MyMemory para traducir el texto proporcionado desde inglés a español.
     * @param httpClient
     * @param nombre
     * @return String
     * @throws IOException
     * @throws InterruptedException
     */
    public static String traduccionENES(HttpClient httpClient, String nombre) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.
                newBuilder().
                header("Content-Type", "application/json").
                uri(URI.create("https://api.mymemory.translated.net/get?q="+ URLEncoder.encode(nombre, StandardCharsets.UTF_8) + "&langpair=en|es".replace("|", "%7C"))).
                GET().build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        TraduccionRecibirPeticionDto pais = new Gson().fromJson(response.body(), TraduccionRecibirPeticionDto.class);
        return pais.responseData().translatedText();
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
    public static String traduccion(String nombre, String idiomaActual, String idiomaATraduccion) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.
                newBuilder().
                header("Content-Type", "application/json").
                uri(URI.create("https://api.mymemory.translated.net/get?q="+ URLEncoder.encode(nombre, StandardCharsets.UTF_8) + "&langpair="+idiomaActual+"|".replace("|", "%7C")+idiomaATraduccion)).
                GET().build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        TraduccionRecibirPeticionDto pais = new Gson().fromJson(response.body(), TraduccionRecibirPeticionDto.class);
        return pais.responseData().translatedText();
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
    public static String traduccion(HttpClient httpClient, String nombre, String idiomaActual, String idiomaATraduccion) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.
                newBuilder().
                header("Content-Type", "application/json").
                uri(URI.create("https://api.mymemory.translated.net/get?q="+ URLEncoder.encode(nombre, StandardCharsets.UTF_8) + "&langpair="+idiomaActual.toLowerCase()+"|".replace("|", "%7C")+idiomaATraduccion.toLowerCase())).
                GET().build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        TraduccionRecibirPeticionDto pais = new Gson().fromJson(response.body(), TraduccionRecibirPeticionDto.class);
        return pais.responseData().translatedText();
    }
}
