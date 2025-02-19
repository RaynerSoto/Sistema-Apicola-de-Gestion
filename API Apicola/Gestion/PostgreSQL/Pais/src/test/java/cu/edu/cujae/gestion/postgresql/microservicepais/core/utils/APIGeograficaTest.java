package cu.edu.cujae.gestion.postgresql.microservicepais.core.utils;

import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.apiGeografica.APIGeoDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class APIGeograficaTest {

    @Test
    void obtenerDatosPaises() throws IOException, InterruptedException {
        //String pais = APITraduction.traduccionESEN("México");
        APIGeoDto apiGeoDto = APIGeografica.obtenerDatosPaises("México");
        System.out.println(apiGeoDto.spa().common());
    }
}