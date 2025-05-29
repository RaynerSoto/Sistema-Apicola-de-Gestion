package cu.edu.cujae.gestion.postgresql.geografia.core.service.implement;
import cu.edu.cujae.gestion.postgresql.geografia.core.service.ContinenteService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para el servicio ContinenteServiceImplement.
 * Esta clase contiene pruebas unitarias para verificar el correcto funcionamiento
 * de los métodos del servicio ContinenteServiceImplement.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContinenteServiceImplementTest {
    private final ContinenteService continenteService;
    private final Logger logger = Logger.getLogger(ContinenteServiceImplementTest.class.getName());

    @Autowired
    ContinenteServiceImplementTest(ContinenteService continenteService) {
        this.continenteService = continenteService;
    }

    @Test
    void listarContinentes() {
        try {
            var continentes = continenteService.listarContinentes();
            assertNotNull(continentes);
            assertFalse(continentes.isEmpty());
            logger.info("Lista de continentes obtenida correctamente.");
        } catch (Exception e) {
            logger.severe("Error al listar los continentes: " + e.getMessage());
            fail("No se pudo listar los continentes: " + e.getMessage());
        }
    }

    @Test
    void buscarContinente() {
        try {
            var continente = continenteService.buscarContinente("Europa");
            assertNotNull(continente);
            assertEquals("Europa", continente.getNombre());
            logger.info("Continente encontrado correctamente: " + continente.getNombre());
        } catch (Exception e) {
            logger.severe("Error al buscar el continente: " + e.getMessage());
            fail("No se pudo buscar el continente: " + e.getMessage());
        }
    }
}