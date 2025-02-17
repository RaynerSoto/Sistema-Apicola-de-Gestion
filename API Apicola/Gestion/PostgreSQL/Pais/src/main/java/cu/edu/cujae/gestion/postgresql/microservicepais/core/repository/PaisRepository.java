package cu.edu.cujae.gestion.postgresql.microservicepais.core.repository;

import cu.edu.cujae.gestion.postgresql.microservicepais.core.model.Pais;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaisRepository extends JpaRepository<Pais,Long> {
    public Optional<Pais> findByIdPaisEquals(Long id);

    public Optional<Pais> findByNombreEquals(String nombre);

    boolean existsPaisByNombre(String nombre);

}
