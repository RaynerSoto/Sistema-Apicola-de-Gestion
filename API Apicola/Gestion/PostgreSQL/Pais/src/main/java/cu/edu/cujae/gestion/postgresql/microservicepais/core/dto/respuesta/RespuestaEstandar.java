package cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.respuesta;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaEstandar {
    @NotBlank(message = "El valor de la respuesta no puede ser nulo")
    private Object respuesta;
}
