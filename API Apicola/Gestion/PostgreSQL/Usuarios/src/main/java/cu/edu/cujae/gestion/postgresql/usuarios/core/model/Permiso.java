package cu.edu.cujae.gestion.postgresql.usuarios.core.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "permisos",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "permiso_seq")
    @SequenceGenerator(name = "permiso_seq", sequenceName = "permisos_id_permiso_seq",initialValue = 1,allocationSize = 1)
    @Column(name = "id_permiso")
    @Schema(name = "Identificador del Permiso", description = "Identificador único del permiso", example = "1")
    private Long idPermiso;

    @Column(name = "nombre_permiso",nullable = false)
    @Schema(name = "Nombre del Permiso", description = "Nombre del permiso, por ejemplo: 'crear usuario'", example = "crear usuario")
    private String nombrePermiso;

    @Column(name = "descripcion",nullable = false)
    @Schema(name = "Descripción del Permiso", description = "Descripción del permiso, por ejemplo: 'Permite crear nuevos usuarios'", example = "Permite crear nuevos usuarios")
    private String descripcion;

    //Falta implementar unión de Roles con los permisos

}
