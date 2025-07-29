package cu.edu.cujae.gestion.postgresql.geografia.core.repository;
import cu.edu.cujae.gestion.postgresql.geografia.core.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio para gestionar las operaciones CRUD de la entidad Pais.
 * Este repositorio extiende JpaRepository para proporcionar métodos predefinidos
 * para acceder a los datos de los países en la base de datos.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
public interface PaisRepository extends JpaRepository<Pais,Long> {
    Optional<Pais> findByIdPaisEquals(Long id);

    Optional<Pais> findByNombreEquals(String nombre);

    boolean existsPaisByNombre(String nombre);

    boolean existsPaisByIdPais(Long idPais);
}
