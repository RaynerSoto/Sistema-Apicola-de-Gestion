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
@Table(name = "roles",schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "roles_seq")
    @SequenceGenerator(name = "roles_seq", sequenceName = "roles_id_seq",initialValue = 1,allocationSize = 1)
    @Column(name = "id_rol",nullable = false,unique = true,updatable = false)
    @Schema(name = "Identificador del Rol", description = "Identificador único del rol", example = "1")
    private Long id_rol;

    @Column(name = "nombre",unique = true,nullable = false,length = 100)
    @Size(min = 4,max = 100,message = "El nombre del rol se encuentra entre 4 y 100 caracteres")
    @NotBlank(message = "El nombre del rol no puede ser nulo o estar vacío")
    @Schema(name = "Nombre del Rol", description = "Nombre del rol, por ejemplo: ADMIN, USER", example = "ADMIN")
    private String nombre;

    @Column(name = "descripcion",unique = true,nullable = false,length = 100)
    @Size(min = 4,max = 100,message = "La descripcion del rol se encuentra entre 4 y 100 caracteres")
    @NotBlank(message = "La descripcion del rol no puede ser nulo o estar vacío")
    @Schema(name = "Descripción del Rol", description = "Descripción del rol, por ejemplo: Administrador del sistema", example = "Administrador del sistema")
    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "rol")
    @Schema(name = "Lista de Usuarios", description = "Lista de usuarios asociados a este rol")
    private List<Usuario> usuarios;
}
