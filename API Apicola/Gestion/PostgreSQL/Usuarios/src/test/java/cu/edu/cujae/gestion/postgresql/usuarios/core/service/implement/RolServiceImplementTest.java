package cu.edu.cujae.gestion.postgresql.usuarios.core.service.implement;

import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Rol;
import cu.edu.cujae.gestion.postgresql.usuarios.core.repository.RolRepository;
import cu.edu.cujae.gestion.postgresql.usuarios.core.service.RolService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Implementación del test de servicio de roles.
 * Proporciona pruebas para verificar el correcto funcionamiento de los métodos del servicio de roles.
 * Esta clase utiliza JUnit 5 para la ejecución de pruebas y Spring Boot para la configuración del contexto de la aplicación.
 * @see cu.edu.cujae.gestion.postgresql.usuarios.core.service.RolService
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RolServiceImplementTest {
    private final RolService rolService;

    @Autowired
    RolServiceImplementTest(RolService rolService) {
        this.rolService = rolService;
    }

    /**
     * Prueba para verificar que el listado de roles no sea nulo.
     * Esta prueba asegura que el método listadoRoles() del servicio de roles
     */
    @Test
    @Order(1)
    void listadoRolesNombre() {
        List<String> roles = rolService.listadoRoles();
        assertFalse(roles.isEmpty(), "La lista de roles no debe ser nula");

    }

    /**
     * Prueba para verificar que el listado de roles no esté vacío.
     * Esta prueba asegura que el método listadoRoles() del servicio de roles
     */
    @Test
    @Order(2)
    void listadoRolesNotEmpty() {
        List<String> roles = rolService.listadoRoles();
        assertFalse(roles.isEmpty(), "La lista de roles no debe estar vacía");
    }
}