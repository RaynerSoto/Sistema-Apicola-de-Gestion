package cu.edu.cujae.gestion.postgresql.microservicepais.core.mapping;

import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.recibir.PaisDto;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.utils.Validacion;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Geometry;

@Entity
@Table(name = "paises")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_seq")
    @SequenceGenerator(name = "pais_seq", sequenceName = "secuence_id_pais",initialValue = 1,allocationSize = 1)
    @Column(name = "id_pais",nullable = false,unique = true,updatable = false)
    private Long idPais;

    @Column(name = "nombre",unique = true,nullable = false,length = 100)
    @Size(min = 4,max = 100,message = "El nombre del país se encuentra entre 4 y 100 caracteres")
    @NotBlank(message = "El nombre del país no puede ser nulo o estar vacío")
    private String nombre;

    @Column(name = "sigla",unique = true,nullable = false,length = 100)
    @Size(min = 1,max = 10,message = "La sigla del país se encuentra entre 1 y 10 caracteres")
    @NotBlank(message = "La sigla del país no puede ser nulo o estar vacío")
    private String sigla;

    @Column(name = "poblacion")
    @Min(value = 0, message = "Los valores de problación deben 0 o positivos")
    private Long poblacion;

    @Column(name = "geometria",columnDefinition = "geometry")
    private Geometry geometry;

    @Column(name = "centroide",columnDefinition = "geometry")
    private Geometry centroide;

    @Column(name = "nombre_continente",nullable = false)
    @Size(min = 4,max = 100,message = "El nombre del continente se encuentra entre 4 y 100 caracteres")
    @NotBlank(message = "El nombre del continente no puede ser nulo o estar vacío")
    private String nombreContinente;

    @Column(name = "codigo",unique = true,nullable = false,length = 10)
    @Size(min = 1,max = 10,message = "El código del país se encuentra entre 1 y 10 caracteres")
    @NotBlank(message = "El código del país no puede ser nulo o estar vacío")
    private String codigo;

    @Column(name = "areakm2",nullable = false)
    @Min(value = 0, message = "Los valores de problación deben 0 o positivos")
    private Double areaKM2;

    @PrePersist
    public void prePersist(){
        Validacion.validarUnsupportedOperationException(this);
    }

    public Pais(PaisDto paisDto) {
        this.nombre = paisDto.nombre();
        this.sigla = paisDto.sigla();
        this.poblacion = paisDto.poblacion();
        this.geometry = paisDto.geometry();
        this.centroide = paisDto.centroide();
        this.areaKM2 = paisDto.areaKM2();
        this.codigo = paisDto.codigo();
        this.nombreContinente = paisDto.nombre_continente();
    }
}
