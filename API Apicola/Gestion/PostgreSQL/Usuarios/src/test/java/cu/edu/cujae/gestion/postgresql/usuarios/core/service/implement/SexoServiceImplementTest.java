package cu.edu.cujae.gestion.postgresql.usuarios.core.service.implement;

import cu.edu.cujae.gestion.postgresql.usuarios.core.service.SexoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para la implementación del servicio SexoService.
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SexoServiceImplementTest {
    private final SexoService sexoService;
    private static final Logger logger = LoggerFactory.getLogger(SexoServiceImplementTest.class);

    @Autowired
    SexoServiceImplementTest(SexoService sexoService) {
        this.sexoService = sexoService;
    }

    @Test
    @Order(1)
    void listadoSexosNombre() {
        logger.info("Iniciando prueba de listado de sexos por nombre");
        assertFalse(sexoService.listadoSexos().isEmpty(), "La lista de sexos no debe ser nula");
        logger.info("Terminando la prueba con éxito");
    }

}