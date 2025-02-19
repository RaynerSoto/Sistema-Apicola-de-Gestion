package cu.edu.cujae.gestion.postgresql.estado.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Geometry;

@Entity
@Table(name = "estados")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "estado_seq")
    @SequenceGenerator(name = "estado_seq", sequenceName = "estados_id_estados_seq",initialValue = 1,allocationSize = 1)
    @Column(name = "id_estado",nullable = false,unique = true,updatable = false)
    private Long idEstado;

    @Column(name = "nombre",unique = true,nullable = false,length = 100)
    @Size(min = 4,max = 100,message = "El nombre del estado se encuentra entre 4 y 100 caracteres")
    @NotBlank(message = "El nombre del estado no puede ser nulo o estar vacío")
    private String nombre;

    @Column(name = "poblacion")
    @Min(value = 0, message = "Los valores de problación deben 0 o positivos")
    private Long poblacion;

    @Column(name = "geometria",columnDefinition = "geometry")
    private Geometry geometry;

    @Column(name = "centroide",columnDefinition = "geometry")
    private Geometry centroide;

    private Long idPais;

    @Column(name = "codigo",unique = true,nullable = false,length = 100)
    @Size(min = 1,max = 10,message = "El código del estado se encuentra entre 1 y 10 caracteres")
    @NotBlank(message = "El código del estado no puede ser nulo o estar vacío")
    private String codigo;

    @Column(name = "areakm2",nullable = false)
    @Min(value = 0, message = "Los valores del área deben 0 o positivos")
    private double areaKm2;
}
