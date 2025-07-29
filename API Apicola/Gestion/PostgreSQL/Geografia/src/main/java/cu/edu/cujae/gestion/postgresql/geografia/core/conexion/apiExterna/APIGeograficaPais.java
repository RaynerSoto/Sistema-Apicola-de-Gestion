package cu.edu.cujae.gestion.postgresql.geografia.core.conexion.apiExterna;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cu.edu.cujae.gestion.postgresql.geografia.core.dto.apiGeografica.APIGeoPaisDto;
import cu.edu.cujae.gestion.postgresql.geografia.core.exception.APIException;
import cu.edu.cujae.gestion.postgresql.geografia.core.exception.SearchException;
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

/**
 * Clase que se encarga de realizar la conexión con la API externa de RestCountries para obtener información de un país específico.
 * Esta clase utiliza el cliente HTTP de Java para enviar solicitudes GET a la API y recibir respuestas en formato JSON.
 * La URL de la API se inyecta desde el archivo de propiedades de la aplicación.
 * La clase maneja excepciones relacionadas con la conexión a la API y el procesamiento de la respuesta.
 * La información obtenida se convierte a un objeto de tipo APIGeoPaisDto utilizando la biblioteca Gson.
 * Esta clase es un componente de Spring, lo que permite su inyección en otras partes de la aplicación.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
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
     * Método que obtiene los datos de un país específico a través de la API externa de RestCountries.
     * Este método realiza una solicitud GET a la URL de la API con el nombre del país como parámetro.
     * Si la solicitud es exitosa, se convierte la respuesta JSON en un objeto APIGeoPaisDto.
     * Si ocurre un error durante la conexión o el procesamiento de la respuesta, se lanza una APIException con un mensaje descriptivo.
     * @param nombre El nombre del país del cual se desea obtener información.
     * @return Un objeto APIGeoPaisDto que contiene la información del país solicitado.
     * @throws IOException Si ocurre un error de entrada/salida durante la conexión a la API.
     * @throws InterruptedException Si la operación es interrumpida durante la solicitud HTTP.
     * @throws APIException Si ocurre un error al procesar la respuesta de la API o al establecer la conexión.
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
