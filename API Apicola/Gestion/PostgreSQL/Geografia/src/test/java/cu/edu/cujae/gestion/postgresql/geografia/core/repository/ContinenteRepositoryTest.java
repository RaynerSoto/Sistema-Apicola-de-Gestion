package cu.edu.cujae.gestion.postgresql.geografia.core.repository;

import cu.edu.cujae.gestion.postgresql.geografia.core.model.Continente;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para el repositorio ContinenteRepository.
 * Estas pruebas verifican el correcto funcionamiento de los métodos
 * del repositorio para la entidad Continente.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContinenteRepositoryTest {
    private final ContinenteRepository continenteRepository;
    private final Logger logger = Logger.getLogger(ContinenteRepositoryTest.class.getName());

    @Autowired
    ContinenteRepositoryTest(ContinenteRepository continenteRepository) {
        this.continenteRepository = continenteRepository;
    }

    @Test
    void findByNombreEqualsIgnoreCase() {
        logger.info("Iniciando prueba findByNombreEqualsIgnoreCase en ContinenteRepositoryTest");
        var continente = continenteRepository.findByNombreEqualsIgnoreCase("América");
        logger.info("Continente encontrado: " + ((Optional<Continente>)continente).get().getNombre());
        assertTrue(continente.isPresent(), "El continente no debe estar vacío");
        logger.info("Fin prueba findByNombreEqualsIgnoreCase en ContinenteRepositoryTest");
    }
}