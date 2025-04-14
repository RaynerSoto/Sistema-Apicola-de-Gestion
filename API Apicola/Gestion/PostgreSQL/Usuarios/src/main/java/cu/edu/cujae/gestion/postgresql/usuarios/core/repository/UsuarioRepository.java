package cu.edu.cujae.gestion.postgresql.usuarios.core.repository;

import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByUsernameEqualsIgnoreCase(String username);
}
