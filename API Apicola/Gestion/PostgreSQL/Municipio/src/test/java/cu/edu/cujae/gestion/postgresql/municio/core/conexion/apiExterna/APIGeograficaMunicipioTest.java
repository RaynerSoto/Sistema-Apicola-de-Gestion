package cu.edu.cujae.gestion.postgresql.municio.core.conexion.apiExterna;

import cu.edu.cujae.gestion.postgresql.municio.core.dto.apiGeografica.APIGeoMunicipioDto;
import cu.edu.cujae.gestion.postgresql.municio.core.dto.recibir.PaisEstadoDto;
import cu.edu.cujae.gestion.postgresql.municio.core.exception.APIException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.config.import=optional:classpath:application.yml")
class APIGeograficaMunicipioTest {

    @Test
    void obtenerDatosMunicipio() throws APIException, IOException, InterruptedException {
        APIGeoMunicipioDto apiGeoMunicipioDto = APIGeograficaMunicipio.obtenerDatosMunicipio(new PaisEstadoDto("01","CU"));
        assertNotNull(apiGeoMunicipioDto);
        System.out.println(apiGeoMunicipioDto);
    }
}