package cu.edu.cujae.gestion.postgresql.usuarios.core.repository;

import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol,Long> {
    Optional<Rol> findByNombreEquals(String nombre);

    boolean existsByNombreEquals(String nombre);
}
