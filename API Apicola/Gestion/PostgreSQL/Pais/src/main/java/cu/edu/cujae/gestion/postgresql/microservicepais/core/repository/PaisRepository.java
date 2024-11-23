package cu.edu.cujae.gestion.postgresql.microservicepais.core.repository;

import cu.edu.cujae.gestion.postgresql.microservicepais.core.mapping.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaisRepository extends JpaRepository<Pais,Long> {
    public Optional<Pais> findByIdPaisEquals(Long id);
}
