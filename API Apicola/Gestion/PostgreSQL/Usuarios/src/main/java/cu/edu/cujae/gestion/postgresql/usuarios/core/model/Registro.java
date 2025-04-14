package cu.edu.cujae.gestion.postgresql.usuarios.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "registros")
@AllArgsConstructor
@NoArgsConstructor
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "registro_seq")
    @SequenceGenerator(name = "registro_seq", sequenceName = "registros_id_registro_seq",initialValue = 1,allocationSize = 1)
    @Column(name = "id_registro")
    private Long idRegistro;

    @NotBlank(message = "La función no debe encontrarse null o vacía")
    @Column(name = "funcion",updatable = false,nullable = false)
    private String funcion;

    @Column(name = "resultado",updatable = false,nullable = false)
    @NotBlank(message = "El resultado no debe encontrarse null o vacía")
    private String resultado;

    @Column(name = "fecha",updatable = false,nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "La fecha no debe ser null o encontrase vacía")
    private Timestamp fecha;

    private long usuarioId;

}
