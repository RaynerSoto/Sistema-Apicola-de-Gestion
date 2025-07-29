package cu.edu.cujae.gestion.postgresql.geografia.core.dto.paisEstado;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PaisEstadoDtoNombre(
        @Size(min = 4,max = 100,message = "El nombre del país se encuentra entre 4 y 100 caracteres")
        @NotBlank(message = "El nombre del país no puede ser nulo o estar vacío")
        String nombrePais,

        @Size(min = 4,max = 100,message = "El nombre del estado se encuentra entre 4 y 100 caracteres")
        @NotBlank(message = "El nombre del estado no puede ser nulo o estar vacío")
        String nombreEstado) {
}
