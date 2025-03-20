package cu.edu.cujae.gestion.postgresql.estado.core.conexion.standard;

import cu.edu.cujae.gestion.postgresql.estado.core.dto.enviar.PaisEstadoDto;
import cu.edu.cujae.gestion.postgresql.estado.core.dto.respuestaGenerica.RespuestaEstandar;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MunicipioConexionTest {

    @Test
    void municipioInsertar() throws IOException, InterruptedException {
        RespuestaEstandar respuestaEstandar = MunicipioConexion.municipioInsertar(new PaisEstadoDto("01","CU"));
    }
}