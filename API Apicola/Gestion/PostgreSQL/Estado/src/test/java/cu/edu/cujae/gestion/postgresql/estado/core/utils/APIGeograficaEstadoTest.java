package cu.edu.cujae.gestion.postgresql.estado.core.utils;

import cu.edu.cujae.gestion.postgresql.estado.core.conexion.apiExterna.APIGeograficaEstado;
import cu.edu.cujae.gestion.postgresql.estado.core.dto.apiGeografica.APIGeoEstadoDto;
import cu.edu.cujae.gestion.postgresql.estado.core.exception.APIException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class APIGeograficaEstadoTest {
    @Test
    public void test() throws IOException, InterruptedException, APIException {
        APIGeoEstadoDto estados = APIGeograficaEstado.obtenerDatosEstado("USA");
        System.out.println(estados);
    }
}