package cu.edu.cujae.gestion.postgresql.usuarios.core.dto.paisEstado;

import jakarta.validation.constraints.Min;

public record PaisEstadoDtoID(
        @Min(value = 1,message = "El id mínimo debe ser 1")
        Long idPais,

        @Min(value = 1,message = "El id mínimo debe ser 1")
        Long idEstado) {
}
