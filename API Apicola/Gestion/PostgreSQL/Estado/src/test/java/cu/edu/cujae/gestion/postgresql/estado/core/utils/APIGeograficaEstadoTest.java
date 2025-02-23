package cu.edu.cujae.gestion.postgresql.estado.core.utils;

import cu.edu.cujae.gestion.postgresql.estado.core.dto.apiGeografica.APIGeoEstadoDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class APIGeograficaEstadoTest {
    @Test
    public void test() throws IOException, InterruptedException {
        APIGeoEstadoDto estados = APIGeograficaEstado.obtenerDatosEstado("USA");
        System.out.println(estados);
    }
}