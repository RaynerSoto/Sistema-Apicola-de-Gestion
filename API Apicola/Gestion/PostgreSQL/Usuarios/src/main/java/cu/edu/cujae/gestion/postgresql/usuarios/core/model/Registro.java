package cu.edu.cujae.gestion.postgresql.usuarios.core.model;

import cu.edu.cujae.gestion.postgresql.usuarios.core.utils.Validacion;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "registros",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "registro_seq")
    @SequenceGenerator(name = "registro_seq", sequenceName = "registros_id_registro_seq",initialValue = 1,allocationSize = 1)
    @Column(name = "id_registro")
    @Schema(name = "Identificador del Registro", description = "Identificador único del registro", example = "1")
    private Long idRegistro;

    @NotBlank(message = "La función no debe encontrarse null o vacía")
    @Column(name = "funcion",updatable = false,nullable = false)
    @Schema(name = "Función del Registro", description = "Función que se está registrando, por ejemplo: 'crear usuario'", example = "crear usuario")
    private String funcion;

    @Column(name = "resultado",updatable = false,nullable = false)
    @NotBlank(message = "El resultado no debe encontrarse null o vacía")
    @Schema(name = "Resultado del Registro", description = "Resultado de la función registrada, por ejemplo: 'éxito' o 'error'", example = "éxito")
    private String resultado;

    @Column(name = "fecha",updatable = false,nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "La fecha no debe ser null o encontrase vacía")
    @Schema(name = "Fecha del Registro", description = "Fecha y hora en que se realizó el registro", example = "2023-10-01T12:00:00Z")
    private Timestamp fecha;

    @Schema(name = "ID del Usuario", description = "Identificador del usuario que realizó la acción registrada", example = "123")
    private long usuarioId;

    @PrePersist
    @PreUpdate
    public void prePersistUpdate(){
        Validacion.validarElemento(this);
    }

}
