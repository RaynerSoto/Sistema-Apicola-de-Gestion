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

/**
 * Clase que se encarga de realizar la conexión con la API externa de GeoNames para obtener información de un municipio específico.
 * Esta clase utiliza el cliente HTTP de Java para enviar solicitudes GET a la API y recibir respuestas en formato JSON.
 * La URL de la API se inyecta desde el archivo de propiedades de la aplicación.
 * La clase maneja excepciones relacionadas con la conexión a la API y el procesamiento de la respuesta.
 * La información obtenida se convierte a un objeto de tipo APIGeoMunicipioDto utilizando la biblioteca Gson.
 * Esta clase es un componente de Spring, lo que permite su inyección en otras partes de la aplicación.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
@Component
public class APIGeograficaMunicipio {
    @Value("${enlaces.geografia_municipio}")
    private String urlInyectada;

    private static String url; // Esta será la que se usará en el método estático

    @PostConstruct
    private void init() {
        url = urlInyectada;
    }

    /**
     * Método que obtiene los datos de un municipio específico a través de la API externa de GeoNames.
     * Este método realiza una solicitud GET a la URL de la API con el código administrativo y el código del país como parámetros.
     * Si la solicitud es exitosa, se convierte la respuesta JSON en un objeto APIGeoMunicipioDto.
     * Si ocurre un error durante la conexión o el procesamiento de la respuesta, se lanza una APIException con un mensaje descriptivo.
     * @param adminCode El código administrativo del municipio del cual se desea obtener información.
     * @param paisCode El código del país al que pertenece el municipio.
     * @return Un objeto APIGeoMunicipioDto que contiene la información del municipio solicitado.
     * @throws IOException Si ocurre un error de entrada/salida durante la conexión a la API.
     * @throws InterruptedException Si la operación es interrumpida durante la solicitud HTTP.
     * @throws APIException Si ocurre un error al procesar la respuesta de la API o al establecer la conexión.
     */
    public static APIGeoMunicipioDto obtenerDatosMunicipio(String adminCode, String paisCode) throws IOException, InterruptedException, APIException {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.
                    newBuilder().
                    header("Content-Type", "application/json").
                    uri(URI.create(String.format(url,adminCode,paisCode))).
                    GET().
                    build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), APIGeoMunicipioDto.class);
        }catch (Exception e){
            throw new APIException("La conexión con la API de GeoName ha sido defectuosa. Por favor contacte con la API o contacte al servicio técnico del sistema");
        }
    }
}
