package cu.edu.cujae.gestion.postgresql.usuarios.core.model;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.paisEstado.PaisEstadoDtoID;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.usuario.UsuarioDto;
import cu.edu.cujae.gestion.postgresql.usuarios.core.utils.Validacion;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios", schema = "public")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "usuario_seq")
    @SequenceGenerator(name = "usuario_seq", sequenceName = "users_id_seq",initialValue = 1,allocationSize = 1)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "identificacion", nullable = false, length = 50)
    @NotBlank(message = "La identificación del usuario no puede estar estar vacía o ser nula")
    private String identificacion;

    @Column(name = "nombre", nullable = false, length = 255)
    @NotBlank(message = "El nombre completo del usuario no puede estar estar vacía o ser nula")
    private String nombre;

    @Column(name = "username", nullable = false, length = 255)
    @NotBlank(message = "El nombre de usuario no puede estar estar vacía o ser nula")
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    @NotBlank(message = "La contraseña del usuario no puede estar estar vacía o ser nula")
    private String password;

    @Column(name = "email", nullable = false, length = 100)
    @Email(message = "El correo no tiene el formato específico")
    private String email;

    @Column(name = "id_estado", nullable = false)
    @Min(message = "El valor mínimo del identificador del estado es 1", value = 0L)
    private Long idEstado;

    @Column(name = "id_pais", nullable = false)
    @Min(message = "El valor mínimo del nivel identificador del estado es 1", value = 0L)
    private Long idPais;

    @Column(name = "direccion_particular", length = 255)
    @NotBlank(message = "La dirección particular  del usuario no puede estar estar vacía o ser nula")
    private String direccionParticular;

    @Column(name = "geo_posicion",columnDefinition = "point")
    private Point geoPosicion;

    @Column(name = "telefono", nullable = false, length = 50)
    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Número de teléfono inválido")
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "sexo",nullable = false)
    @NotNull(message = "El valor del sexo no puede ser nulo")
    private Sexo sexo;

    @Column(name = "marca_apicola")
    @NotBlank(message = "El valor de si es una marca Apícola no puede ser null")
    private Boolean marcaApicola;

    @Column(name = "certificado")
    @NotBlank(message = "El valor de si es un apicultor certificado no puede ser null")
    private Boolean certificado;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_rol",nullable = false)
    @NotNull(message = "El valor del rol no puede ser nulo")
    private Rol rol;

    @Column(name = "isActivo", nullable = false)
    private Boolean isActivo;

    public Usuario(Long id, UsuarioDto usuario, PaisEstadoDtoID paisEstadoDtoID, Rol rol, Sexo sexo) {
        this.idUsuario = id;
        this.identificacion = usuario.identificacion();
        this.nombre = usuario.nombre();
        this.username = usuario.username();
        this.password = usuario.password();
        this.email = usuario.email();
        this.idEstado = paisEstadoDtoID.idEstado();
        this.idPais = paisEstadoDtoID.idPais();
        this.direccionParticular = usuario.direccionParticular();
        this.geoPosicion = null;
        this.telefono = usuario.telefono();
        this.sexo = sexo;
        this.marcaApicola = usuario.marcaApicola();
        this.certificado = usuario.certificado();
        this.rol = rol;
        this.isActivo = true;
    }

    public Usuario(UsuarioDto usuario, PaisEstadoDtoID paisEstadoDtoID,Rol rol,Sexo sexo) {
        this.identificacion = usuario.identificacion();
        this.nombre = usuario.nombre();
        this.username = usuario.username();
        this.password = usuario.password();
        this.email = usuario.email();
        this.idEstado = paisEstadoDtoID.idEstado();
        this.idPais = paisEstadoDtoID.idPais();
        this.direccionParticular = usuario.direccionParticular();
        this.geoPosicion = null;
        this.telefono = usuario.telefono();
        this.sexo = sexo;
        this.marcaApicola = usuario.marcaApicola();
        this.certificado = usuario.certificado();
        this.rol = rol;
        this.isActivo = true;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+this.rol.getNombre()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isActivo;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isActivo;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isActivo;
    }

    @Override
    public boolean isEnabled() {
        return this.isActivo;
    }

    @PrePersist
    @PreUpdate
    public void prePersistUpdate(){
        Validacion.validarElemento(this);
    }


}
