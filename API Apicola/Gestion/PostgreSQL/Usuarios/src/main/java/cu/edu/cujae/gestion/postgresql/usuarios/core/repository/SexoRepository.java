package cu.edu.cujae.gestion.postgresql.usuarios.core.repository;

import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Sexo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SexoRepository extends JpaRepository<Sexo,Long> {
}
