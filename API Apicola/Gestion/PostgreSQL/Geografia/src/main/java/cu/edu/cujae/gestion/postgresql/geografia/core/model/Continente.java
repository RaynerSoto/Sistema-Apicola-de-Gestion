package cu.edu.cujae.gestion.postgresql.geografia.core.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "continentes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Continente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_seq")
    @SequenceGenerator(name = "pais_seq", sequenceName = "secuence_id_continente",initialValue = 1,allocationSize = 1)
    @Column(name = "id_continente",nullable = false,unique = true,updatable = false)
    private Long idContinente;

    @Column(name = "nombre",unique = true,nullable = false,length = 100)
    @Size(min = 4,max = 100,message = "El nombre del continente se encuentra entre 4 y 100 caracteres")
    @NotBlank(message = "El nombre del continente no puede ser nulo o estar vacío")
    private String nombre;

    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "continente")
    private List<Pais> paises;
}
