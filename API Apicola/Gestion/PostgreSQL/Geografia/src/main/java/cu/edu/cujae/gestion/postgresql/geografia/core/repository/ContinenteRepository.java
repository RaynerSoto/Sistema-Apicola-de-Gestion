package cu.edu.cujae.gestion.postgresql.geografia.core.repository;
import cu.edu.cujae.gestion.postgresql.geografia.core.model.Continente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio para gestionar las operaciones CRUD de la entidad Continente.
 * Este repositorio extiende JpaRepository para proporcionar métodos predefinidos
 * para acceder a los datos de los continentes en la base de datos.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
public interface ContinenteRepository extends JpaRepository<Continente,Long> {
    Optional<Continente> findByNombreEqualsIgnoreCase(String nombre);
}
