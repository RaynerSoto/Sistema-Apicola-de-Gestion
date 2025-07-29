package cu.edu.cujae.gestion.postgresql.usuarios.core.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "sexos",schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sexo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sexo_seq")
    @SequenceGenerator(name = "sexo_seq", sequenceName = "sexos_id_sexo_seq",initialValue = 1,allocationSize = 1)
    @Column(name = "id_sexo",nullable = false,unique = true,updatable = false)
    @Schema(name = "Identificador del Sexo",description = "Identificador único del sexo",example = "1")
    private Long id_sexo;

    @Column(name = "nombre",unique = true,nullable = false,length = 100)
    @Size(min = 4,max = 100,message = "El nombre del sexo se encuentra entre 4 y 100 caracteres")
    @NotBlank(message = "El nombre del sexo no puede ser nulo o estar vacío")
    @Schema(name = "Nombre del Sexo",description = "Nombre del sexo, puede ser masculino, femenino u otro",example = "Masculino")
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "sexo")
    @Schema(name = "Lista de Usuarios",description = "Lista de usuarios asociados a este sexo")
    private List<Usuario> usuarios;
}
