package cu.edu.cujae.gestion.postgresql.usuarios.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "permisos",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "permiso_seq")
    @SequenceGenerator(name = "permiso_seq", sequenceName = "permisos_id_permiso_seq",initialValue = 1,allocationSize = 1)
    @Column(name = "id_permiso")
    private Long idPermiso;

    @Column(name = "nombre_permiso",nullable = false)
    private String nombrePermiso;

    @Column(name = "descripcion",nullable = false)
    private String descripcion;

    //Falta implementar unión de Roles con los permisos

}
