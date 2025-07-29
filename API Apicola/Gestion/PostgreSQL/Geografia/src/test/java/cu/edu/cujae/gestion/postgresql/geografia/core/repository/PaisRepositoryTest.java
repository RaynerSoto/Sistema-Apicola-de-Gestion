package cu.edu.cujae.gestion.postgresql.geografia.core.repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para el repositorio PaisRepository.
 * Esta clase contiene pruebas unitarias para verificar el correcto funcionamiento
 * de los métodos del repositorio PaisRepository.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PaisRepositoryTest {
    private final PaisRepository paisRepository;
    private Logger logger = Logger.getLogger(PaisRepositoryTest.class.getName());

    @Autowired
    PaisRepositoryTest(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    @Test
    @Order(1)
    void findByIdPaisEquals() {
        logger.info("Ejecutando prueba findByIdPaisEquals");
        var pais = paisRepository.findByIdPaisEquals(1L);
        assertNotNull(pais, "El país no debe ser nulo");
        assertEquals(1L, pais.get().getIdPais(), "El ID del país debe ser 1");
        logger.info("Prueba findByIdPaisEquals completada con éxito");
    }

    @Test
    @Order(2)
    void findByNombreEquals() {
        logger.info("Ejecutando prueba findByNombreEquals");
        var pais = paisRepository.findByNombreEquals("Cuba");
        assertNotNull(pais, "El país no debe ser nulo");
        assertEquals("Cuba", pais.get().getNombre(), "El nombre del país debe ser 'Cuba'");
        logger.info("Prueba findByNombreEquals completada con éxito");
    }

    @Test
    @Order(3)
    void existsPaisByNombre() {
        logger.info("Ejecutando prueba existsPaisByNombre");
        boolean exists = paisRepository.existsPaisByNombre("Cuba");
        assertTrue(exists, "El país 'Cuba' debe existir en la base de datos");
        logger.info("Prueba existsPaisByNombre completada con éxito");
    }

    @Test
    @Order(4)
    void existsPaisByIdPais() {
        logger.info("Ejecutando prueba existsPaisByIdPais");
        boolean exists = paisRepository.existsPaisByIdPais(1L);
        assertTrue(exists, "El país con ID 1 debe existir en la base de datos");
        logger.info("Prueba existsPaisByIdPais completada con éxito");
    }
}