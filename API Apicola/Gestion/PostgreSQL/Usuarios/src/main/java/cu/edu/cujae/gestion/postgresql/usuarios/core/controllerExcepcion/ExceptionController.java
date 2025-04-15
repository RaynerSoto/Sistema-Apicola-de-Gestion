package cu.edu.cujae.gestion.postgresql.usuarios.core.controllerExcepcion;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.*;
import feign.FeignException;
import jakarta.persistence.PersistenceException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {
    private static final Map<String, String> ERROR_MAP = new HashMap<>();

    // Clase que se carga en memoria automaticamente una vez al ejecutarse
    static {
        ERROR_MAP.put("unik_usuarios_email", "Email del usuario ya insertado");
        ERROR_MAP.put("unik_usuarios_identificacion", "Identificación del usuario ya insertado");
        ERROR_MAP.put("unik_usuarios_username", "Nombre del usuario ya insertado");
        ERROR_MAP.put("fk_usuarios_estados_id", "El estado no existe");
        ERROR_MAP.put("fk_usuarios_paises_id", "El país no existe");
        ERROR_MAP.put("fk_usuarios_rol_id", "El rol no existe");
        ERROR_MAP.put("fk_usuarios_sexos_id", "El sexo no existe");
        ERROR_MAP.put("users_pkey", "El usuario viola la clave primaria");
        ERROR_MAP.put("unik_roles_descricion", "La descripción del rol ya existe");
        ERROR_MAP.put("unik_roles_nombre", "La nombre del rol ya existe");
    }

    @ExceptionHandler({SQLException.class, PersistenceException.class, DataAccessException.class})
    public ResponseEntity<RespuestaEstandar> handleSQLException(SQLException ex) {
        // Manejo de excepciones específicas de SQL
        String errorMessage = ERROR_MAP.entrySet().stream()
                .filter(s -> s.getKey().contains(ex.getMessage()))
                .map(Map.Entry::getValue).findFirst().orElse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RespuestaEstandar(errorMessage));
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