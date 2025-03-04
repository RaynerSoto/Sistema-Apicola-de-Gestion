package cu.edu.cujae.gestion.postgresql.estado.core.controllerExcepcion;
import cu.edu.cujae.gestion.postgresql.estado.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.estado.core.exception.APIException;
import cu.edu.cujae.gestion.postgresql.estado.core.exception.AsignacionException;
import cu.edu.cujae.gestion.postgresql.estado.core.exception.SearchException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class ExceptionCotroller {
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<RespuestaEstandar> handleExceptionSQL(SQLException ex) {
        if (ex.getMessage().contains("fk_estado_pais_id"))
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RespuestaEstandar("País no reconocido"));
        else if(ex.getMessage().contains("estados_pkey"))
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RespuestaEstandar("El id del estado ya ha sido insertado o tiene algún error"));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RespuestaEstandar(ex.getMessage()));
    }

    @ExceptionHandler({UnsupportedOperationException.class, APIException.class,Exception.class, SearchException.class, RuntimeException.class, AsignacionException.class})
    public ResponseEntity<RespuestaEstandar> handleException(UnsupportedOperationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RespuestaEstandar(ex.getMessage()));
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<RespuestaEstandar> handleExceptionFeignException(UnsupportedOperationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RespuestaEstandar("Microservicio caído o en mantenimiento, no se puede acceder a él"));
    }
}
