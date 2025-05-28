package cu.edu.cujae.gestion.postgresql.usuarios.core.service.implement;

import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Rol;
import cu.edu.cujae.gestion.postgresql.usuarios.core.repository.RolRepository;
import cu.edu.cujae.gestion.postgresql.usuarios.core.service.RolService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RolServiceImplementTest {
    private final RolService rolService;

    @Autowired
    RolServiceImplementTest(RolService rolService) {
        this.rolService = rolService;
    }

    @Test
    @Order(1)
    void listadoRolesNombre() {
        List<String> roles = rolService.listadoRoles();
        assertFalse(roles.isEmpty(), "La lista de roles no debe ser nula");

    }

    @Test
    @Order(2)
    void listadoRolesNotEmpty() {
        List<String> roles = rolService.listadoRoles();
        assertFalse(roles.isEmpty(), "La lista de roles no debe estar vacía");
    }
}