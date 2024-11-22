package cu.edu.cujae.gestion.postgresql.continente.core.repository;

import cu.edu.cujae.gestion.postgresql.continente.core.mapper.Continente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContinenteRepository extends JpaRepository<Continente,Long> {
    public Optional<Continente> findByNombreEqualsIgnoreCase(String nombre);
}
