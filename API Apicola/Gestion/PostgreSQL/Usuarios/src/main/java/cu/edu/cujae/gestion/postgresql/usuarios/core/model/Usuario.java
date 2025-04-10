package cu.edu.cujae.gestion.postgresql.usuarios.core.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.geom.Point2D;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios", schema = "public")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "identificacion", nullable = false, length = 50)
    private String identificacion;

    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;

    @Column(name = "username", nullable = false, length = 255)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "id_estado", nullable = false)
    private Long idEstado;

    @Column(name = "id_pais", nullable = false)
    private Long idPais;

    @Column(name = "direccion_particular", length = 255)
    private String direccionParticular;

    // Nota: el tipo PostgreSQL 'point' no tiene mapeo directo, puedes usar String o una clase personalizada
    @Column(name = "geo_posicion")
    private String geoPosicion;

    @Column(name = "telefono", nullable = false, length = 50)
    private String telefono;

    @Column(name = "sexo", nullable = false)
    private Long sexo;

    @Column(name = "marca_apicola")
    private Boolean marcaApicola;

    @Column(name = "certificado")
    private Boolean certificado;

    @Column(name = "id_rol", nullable = false)
    private Long idRol;
}
