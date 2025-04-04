package cu.edu.cujae.gestion.postgresql.usuarios.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sexos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sexo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sexo_seq")
    @SequenceGenerator(name = "sexo_seq", sequenceName = "sexos_id_sexo_seq",initialValue = 1,allocationSize = 1)
    @Column(name = "id_continente",nullable = false,unique = true,updatable = false)
    private Long id_sexo;


    @Column(name = "nombre",unique = true,nullable = false,length = 100)
    @Size(min = 4,max = 100,message = "El nombre del sexo se encuentra entre 4 y 100 caracteres")
    @NotBlank(message = "El nombre del sexo no puede ser nulo o estar vacío")
    private String sexo;
}
