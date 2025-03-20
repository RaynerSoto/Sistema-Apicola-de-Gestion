package cu.edu.cujae.gestion.postgresql.geografia.core.repository;

import cu.edu.cujae.gestion.postgresql.estado.core.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado,Long> {
}
