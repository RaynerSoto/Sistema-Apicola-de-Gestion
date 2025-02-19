package cu.edu.cujae.gestion.postgresql.microservicepais.core.utils;

import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.apiGeografica.APIGeoPaisDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class APIGeograficaPaisTest {

    @Test
    void obtenerDatosPaises() throws IOException, InterruptedException {
        //String pais = APITraduction.traduccionESEN("México");
        APIGeoPaisDto apiGeoPaisDto = APIGeograficaPais.obtenerDatosPaises("México");
        System.out.println(apiGeoPaisDto.spa().common());
    }
}