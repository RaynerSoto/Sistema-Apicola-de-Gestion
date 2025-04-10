package cu.edu.cujae.gestion.postgresql.usuarios.core.repository;

import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol,Long> {
}
