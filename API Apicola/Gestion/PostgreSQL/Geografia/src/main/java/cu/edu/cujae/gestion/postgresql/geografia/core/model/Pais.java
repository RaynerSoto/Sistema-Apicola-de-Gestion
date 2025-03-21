package cu.edu.cujae.gestion.postgresql.geografia.core.model;
import cu.edu.cujae.gestion.postgresql.geografia.core.utils.Validacion;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

import java.util.List;

@Entity
@Table(name = "paises")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pais_seq")
    @SequenceGenerator(name = "pais_seq", sequenceName = "pais_id_pais_seq",initialValue = 1,allocationSize = 1)
    @Column(name = "id_pais",nullable = false,unique = true,updatable = false)
    private Long idPais;

    @Column(name = "nombre",unique = true,nullable = false,length = 100)
    @Size(min = 4,max = 100,message = "El nombre del país se encuentra entre 4 y 100 caracteres")
    @NotBlank(message = "El nombre del país no puede ser nulo o estar vacío")
    private String nombre;

    @Column(name = "nombre_completo",unique = true,nullable = false,length = 100)
    @Size(min = 4,max = 100,message = "El nombre completo del país se encuentra entre 4 y 100 caracteres")
    @NotBlank(message = "El nombre completo del país no puede ser nulo o estar vacío")
    private String nombreCompleto;

    @Column(name = "sigla",unique = true,nullable = false,length = 100)
    @Size(min = 1,max = 10,message = "La sigla del país se encuentra entre 1 y 10 caracteres")
    @NotBlank(message = "La sigla del país no puede ser nulo o estar vacío")
    private String sigla;

    @Column(name = "poblacion")
    @Min(value = 0, message = "Los valores de problación deben 0 o positivos")
    private Long poblacion;

    @Column(name = "geometria",columnDefinition = "geometry")
    private Polygon geometry;

    @Column(name = "centroide",columnDefinition = "geometry")
    private Point centroide;

    @ManyToOne
    private Continente continente;

    @OneToMany(mappedBy = "pais",cascade = CascadeType.ALL)
    List<Estado> estados;

    @Column(name = "areakm2",nullable = false)
    @Min(value = 0, message = "Los valores del área deben 0 o positivos")
    private Double areaKM2;

    @PrePersist
    @PreUpdate
    public void prePersist(){
        Validacion.validarElemento(this);
    }
}
