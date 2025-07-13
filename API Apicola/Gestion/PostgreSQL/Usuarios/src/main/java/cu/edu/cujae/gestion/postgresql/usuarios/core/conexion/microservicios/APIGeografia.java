package cu.edu.cujae.gestion.postgresql.usuarios.core.conexion.microservicios;

import com.google.gson.Gson;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.paisEstado.PaisEstadoDtoNombre;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.APIException;
import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.SearchException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Clase que se encarga de realizar las peticiones a la API de Geografía.
 * Esta clase contiene métodos estáticos que permiten comprobar la existencia de un país y estado
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
@Component
public class APIGeografia {

    @Value("${enlaces.geografia_microservicio_geo}")
    private String urlInyectada;

    private static String url; // Esta será la que se usará en el método estático

    @PostConstruct
    private void init() {
        url = urlInyectada; // Se asigna el valor inyectado a la variable estática
    }

    public static RespuestaEstandar comprobarPaisEstadoID(PaisEstadoDtoNombre paisEstado) throws IOException, InterruptedException, APIException, SearchException {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.
                    newBuilder().
                    header("Content-Type", "application/json").
                    uri(URI.create(url + "PaisEstado")).
                    POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(paisEstado))).
                    timeout(Duration.ofMinutes(30)).
                    build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            RespuestaEstandar respuestaEstandar = new Gson().fromJson(response.body(), RespuestaEstandar.class);
            if (respuestaEstandar.getRespuesta() instanceof String) {
                throw new SearchException((String) respuestaEstandar.getRespuesta());
            }
            return respuestaEstandar;
        }catch (SearchException e){
            throw new SearchException(e.getMessage());
        }catch (Exception e){
            throw new APIException("La conexión con la API de Geografia para la comprobación de los datos ha sido defectuosa, contactar con el servicio técnico");
        }
    }

    public static RespuestaEstandar comprobarPaisEstadoNombre(Long pais,Long estado) throws IOException, InterruptedException, APIException, SearchException {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.
                    newBuilder().
                    header("Content-Type", "application/json").
                    uri(URI.create(url + String.format("PaisEstado/%s/%s", pais, estado))).
                    GET().
                    build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            RespuestaEstandar respuestaEstandar = new Gson().fromJson(response.body(), RespuestaEstandar.class);
            if (respuestaEstandar.getRespuesta() instanceof String) {
                throw new SearchException((String) respuestaEstandar.getRespuesta());
            }
            return respuestaEstandar;
        }catch (SearchException e){
            throw new SearchException(e.getMessage());
        }catch (Exception e){
            throw new APIException("La conexión con la API de Geografia para la comprobación de los datos ha sido defectuosa, contactar con el servicio técnico");
        }
    }

}
