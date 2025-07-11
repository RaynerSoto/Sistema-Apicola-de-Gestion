package cu.edu.cujae.gestion.postgresql.usuarios.core.repository;

import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Rol;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RolRepositoryTest {


    private final RolRepository rolRepository;
    Logger logger = Logger.getLogger(RolRepositoryTest.class.getName());

    @Autowired
    RolRepositoryTest(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Test
    void findByNombreEquals() {
        String nombre = "Administrador";
        Rol foundRol = rolRepository.findByNombreEquals(nombre).orElse(null);
        assertNotNull(foundRol, "Rol should be found");
        assertEquals(nombre, foundRol.getNombre(), "Rol name should match");
        logger.info(foundRol.getNombre());
    }

    @Test
    void existsByNombreEquals() {
        String nombre = "Administrador";
        boolean exists = rolRepository.existsByNombreEquals(nombre);
        assertTrue(exists, "Rol should exist");
        logger.info("Rol exists: " + exists);
    }
}