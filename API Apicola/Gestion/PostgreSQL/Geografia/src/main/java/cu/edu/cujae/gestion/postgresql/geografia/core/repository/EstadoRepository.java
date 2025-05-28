package cu.edu.cujae.gestion.postgresql.geografia.core.repository;

import cu.edu.cujae.gestion.postgresql.geografia.core.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para gestionar las operaciones CRUD de la entidad Estado.
 * Este repositorio extiende JpaRepository para proporcionar métodos predefinidos
 * para acceder a los datos de los estados en la base de datos.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
public interface EstadoRepository extends JpaRepository<Estado,Long> {
}
