package cu.edu.cujae.gestion.postgresql.geografia.core.dto.respuestaGenerica;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaEstandar2doNivel<T> extends RespuestaEstandar<T>{
    @NotBlank(message = "El valor del mensaje no puede ser nulo")
    private String message;
    public RespuestaEstandar2doNivel(T respuesta, String message) {
        super(respuesta);
        this.message = message;
    }
}
