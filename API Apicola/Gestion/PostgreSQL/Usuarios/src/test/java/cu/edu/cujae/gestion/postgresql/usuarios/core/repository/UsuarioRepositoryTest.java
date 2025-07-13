package cu.edu.cujae.gestion.postgresql.usuarios.core.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UsuarioRepositoryTest {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioRepositoryTest(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Test
    void existsByIdUsuario() {
        // Arrange
        Long id = 1L; // Cambia este ID por uno que exista en tu base de datos

        // Act
        boolean exists = usuarioRepository.existsByIdUsuario(id);

        // Assert
        assertTrue(exists, "El usuario con ID " + id + " debería existir.");
    }
}