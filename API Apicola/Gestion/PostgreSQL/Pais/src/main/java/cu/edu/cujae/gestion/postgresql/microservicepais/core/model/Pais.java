package cu.edu.cujae.gestion.postgresql.microservicepais.core.model;

import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.continente.ContinenteDto;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.recibir.PaisDtoReci;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.utils.Validacion;
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

    @Column(name = "areakm2",nullable = false)
    @Min(value = 0, message = "Los valores del área deben 0 o positivos")
    private Double areaKM2;

    public Pais(PaisDtoReci paisDtoReci, ContinenteDto continente) {
        this.nombre = paisDtoReci.nombre();
        this.sigla = paisDtoReci.sigla();
        if (paisDtoReci.poblacion() != null)
            this.poblacion = paisDtoReci.poblacion();
        else
            this.poblacion = 0L;
        this.geometry = paisDtoReci.geometry();
        this.centroide = paisDtoReci.centroide();
        if (paisDtoReci.areaKM2() != null)
            this.areaKM2 = paisDtoReci.areaKM2();
        else
            this.areaKM2 = 0D;
        this.nombreContinente = continente.getNombre();
    }

    public Pais(PaisDtoReci paisDtoReci, String continente) {
        this.nombre = paisDtoReci.nombre();
        this.sigla = paisDtoReci.sigla();
        if (paisDtoReci.poblacion() != null)
            this.poblacion = paisDtoReci.poblacion();
        else
            this.poblacion = 0L;
        this.geometry = paisDtoReci.geometry();
        this.centroide = paisDtoReci.centroide();
        if (paisDtoReci.areaKM2() != null)
            this.areaKM2 = paisDtoReci.areaKM2();
        else
            this.areaKM2 = 0D;
        this.nombreContinente = continente;
    }

    public Pais(PaisDtoReci paisDtoReci, String continente, Long id) {
        this.idPais = id;
        this.nombre = paisDtoReci.nombre();
        this.sigla = paisDtoReci.sigla();
        if (paisDtoReci.poblacion() != null)
            this.poblacion = paisDtoReci.poblacion();
        else
            this.poblacion = 0L;
        this.geometry = paisDtoReci.geometry();
        this.centroide = paisDtoReci.centroide();
        if (paisDtoReci.areaKM2() != null)
            this.areaKM2 = paisDtoReci.areaKM2();
        else
            this.areaKM2 = 0D;
        this.nombreContinente = continente;
    }

    public Pais(PaisDtoReci paisDtoReci) {
        this.nombre = paisDtoReci.nombre();
        this.sigla = paisDtoReci.sigla();
        if (paisDtoReci.poblacion() != null)
            this.poblacion = paisDtoReci.poblacion();
        else
            this.poblacion = 0L;
        this.geometry = paisDtoReci.geometry();
        this.centroide = paisDtoReci.centroide();
        if (paisDtoReci.areaKM2() != null)
            this.areaKM2 = paisDtoReci.areaKM2();
        else
            this.areaKM2 = 0D;
        this.nombreContinente = paisDtoReci.nombre_continente();
    }

    public Pais(PaisDtoReci paisDtoReci, Long id) {
        this.idPais = id;
        this.nombre = paisDtoReci.nombre();
        this.sigla = paisDtoReci.sigla();
        if (paisDtoReci.poblacion() != null)
            this.poblacion = paisDtoReci.poblacion();
        else
            this.poblacion = 0L;
        this.geometry = paisDtoReci.geometry();
        this.centroide = paisDtoReci.centroide();
        if (paisDtoReci.areaKM2() != null)
            this.areaKM2 = paisDtoReci.areaKM2();
        else
            this.areaKM2 = 0D;
        this.nombreContinente = paisDtoReci.nombre_continente();
    }

    @PrePersist
    @PreUpdate
    public void prePersist(){
        Validacion.validarElemento(this);
    }
}
