package cu.edu.cujae.gestion.postgresql.geografia.core.model;

import cu.edu.cujae.gestion.postgresql.geografia.core.dto.apiGeografica.GeonamesDto;
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

@Entity
@Table(name = "municipios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Municipio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "municipio_seq")
    @SequenceGenerator(name = "municipio_seq", sequenceName = "municipio_id_municipio_seq",initialValue = 1,allocationSize = 1)
    @Column(name = "id_municipio",nullable = false,unique = true,updatable = false)
    private Long idMunicipio;

    @Column(name = "nombre",unique = true,nullable = false,length = 100)
    @Size(min = 4,max = 100,message = "El nombre del municipio se encuentra entre 4 y 100 caracteres")
    @NotBlank(message = "El nombre del municipio no puede ser nulo o estar vacío")
    private String nombre;

    @Column(name = "poblacion")
    @Min(value = 0, message = "Los valores de problación deben 0 o positivos")
    private Long poblacion;

    @Column(name = "geometria",columnDefinition = "geometry")
    private Polygon geometry;

    @Column(name = "centroide",columnDefinition = "geometry")
    private Point centroide;

    @Column(name = "areakm2",nullable = false)
    @Min(value = 0, message = "Los valores del área deben 0 o positivos")
    private Double areaKM2;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private Estado estado;

    @Column(name = "codigo",nullable = false,length = 100)
    @Size(min = 1,max = 10,message = "El código del municipio se encuentra entre 1 y 10 caracteres")
    @NotBlank(message = "El código del municipio no puede ser nulo o estar vacío")
    private String codigo;

    public Municipio(GeonamesDto geonamesDto) {
        this.nombre = geonamesDto.toponymName();
        this.codigo = geonamesDto.adminCode1();
        this.poblacion = geonamesDto.population();
    }

    @PrePersist
    @PreUpdate
    public void prePersist(){
        Validacion.validarElemento(this);
    }
}
