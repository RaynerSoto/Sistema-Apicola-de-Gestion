package cu.edu.cujae.gestion.postgresql.geografia.core.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para el controlador GeografiaController.
 * Esta clase contiene pruebas unitarias para verificar el correcto funcionamiento
 * de los métodos del controlador GeografiaController.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GeografiaControllerTest {
    private final GeografiaController geografiaController;
    private final Logger logger = Logger.getLogger(GeografiaControllerTest.class.getName());

    @Autowired
    public GeografiaControllerTest(GeografiaController geografiaController) {
        this.geografiaController = geografiaController;
    }

    @Test
    @Order(1)
    void insertarDatosGeograficos() {
        logger.info("Iniciando prueba insertarDatosGeograficos en GeografiaControllerTest");
        try {
            geografiaController.insertarDatosGeograficos("Alemania");
            logger.info("Datos geográficos insertados correctamente");
        } catch (Exception e) {
            logger.severe("Error al insertar datos geográficos: " + e.getMessage());
            fail("La inserción de datos geográficos falló");
        }
        logger.info("Fin prueba insertarDatosGeograficos en GeografiaControllerTest");
    }

    @Test
    @Order(2)
    void obtenerPaisEstadoID() {
        logger.info("Iniciando prueba obtenerPaisEstadoID en GeografiaControllerTest");
        try {
            var resultado = geografiaController.obtenerPaisEstadoID(null);
            assertNotNull(resultado, "El resultado no debe ser nulo");
            logger.info("Resultado obtenido: " + resultado);
        } catch (Exception e) {
            logger.severe("Error al obtener país/estado por ID: " + e.getMessage());
            fail("La obtención de país/estado por ID falló");
        }
        logger.info("Fin prueba obtenerPaisEstadoID en GeografiaControllerTest");
    }

    @Test
    @Order(3)
    void obtenerPaisEstadoNombre() {
        logger.info("Iniciando prueba obtenerPaisEstadoNombre en GeografiaControllerTest");
        try {
            var resultado = geografiaController.obtenerPaisEstadoNombre(1L,2L);
            assertNotNull(resultado, "El resultado no debe ser nulo");
            logger.info("Resultado obtenido: " + resultado);
        } catch (Exception e) {
            logger.severe("Error al obtener país/estado por nombre: " + e.getMessage());
            fail("La obtención de país/estado por nombre falló");
        }
        logger.info("Fin prueba obtenerPaisEstadoNombre en GeografiaControllerTest");
    }
}