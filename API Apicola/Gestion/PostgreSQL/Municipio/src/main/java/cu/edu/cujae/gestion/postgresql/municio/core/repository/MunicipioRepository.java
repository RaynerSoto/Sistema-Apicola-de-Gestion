package cu.edu.cujae.gestion.postgresql.municio.core.repository;

import cu.edu.cujae.gestion.postgresql.municio.core.model.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipioRepository extends JpaRepository<Municipio,Long> {
}
