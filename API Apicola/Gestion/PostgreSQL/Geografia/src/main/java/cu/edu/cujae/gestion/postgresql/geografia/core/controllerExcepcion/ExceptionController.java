package cu.edu.cujae.gestion.postgresql.geografia.core.controllerExcepcion;
import cu.edu.cujae.gestion.postgresql.geografia.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.geografia.core.exception.APIException;
import cu.edu.cujae.gestion.postgresql.geografia.core.exception.AsignacionException;
import cu.edu.cujae.gestion.postgresql.geografia.core.exception.SearchException;
import feign.FeignException;
import jakarta.persistence.PersistenceException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({SQLException.class, PersistenceException.class, DataAccessException.class})
    public ResponseEntity<RespuestaEstandar> handleSQLException(SQLException ex) {
        // Manejo de excepciones específicas de SQL
        String errorMessage = getSQLExceptionMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RespuestaEstandar(errorMessage));
    }

    private String getSQLExceptionMessage(String errorMessage) {
        if (errorMessage.contains("nombre_unik_pais"))
            return "País con dicho nombre ya insertado";
        else if (errorMessage.contains("nombre_completo_unik_pais"))
            return "País con dicho nombre completo ya insertado";
        else if (errorMessage.contains("siglas_unik_pais"))
            return "País con dicha sigla ya insertado";
        else if (errorMessage.contains("fk_pais_continente_nombre"))
            return "Continente no registrado";
        else if (errorMessage.contains("pais_pkey"))
            return "El id del país ya ha sido insertado o tiene algún error";
        return errorMessage; // Mensaje genérico en caso de no coincidir con los patrones
    }

    @ExceptionHandler({UnsupportedOperationException.class, APIException.class, Exception.class, SearchException.class, RuntimeException.class, AsignacionException.class})
    public ResponseEntity<RespuestaEstandar> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RespuestaEstandar(ex.getMessage()));
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<RespuestaEstandar> handleFeignException(FeignException ex) {
        // Manejo de excepciones de Feign
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RespuestaEstandar("País insertado, pero microservicio caído o en mantenimiento, no se puede acceder a él"));
    }
}