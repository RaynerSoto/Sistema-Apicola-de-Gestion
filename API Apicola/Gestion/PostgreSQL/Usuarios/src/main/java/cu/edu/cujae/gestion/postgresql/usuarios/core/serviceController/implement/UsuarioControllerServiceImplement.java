package cu.edu.cujae.gestion.postgresql.usuarios.core.serviceController.implement;

import cu.edu.cujae.gestion.postgresql.usuarios.core.conexion.microservicios.APIGeografia;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.paisEstado.PaisEstadoDtoID;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.paisEstado.PaisEstadoDtoNombre;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.usuario.UsuarioDtoEnviar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.usuario.UsuarioDtoRecibir;
import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.SearchException;
import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Rol;
import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Sexo;
import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Usuario;
import cu.edu.cujae.gestion.postgresql.usuarios.core.repository.RolRepository;
import cu.edu.cujae.gestion.postgresql.usuarios.core.repository.SexoRepository;
import cu.edu.cujae.gestion.postgresql.usuarios.core.repository.UsuarioRepository;
import cu.edu.cujae.gestion.postgresql.usuarios.core.service.UsuarioService;
import cu.edu.cujae.gestion.postgresql.usuarios.core.serviceController.UsuarioControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioControllerServiceImplement implements UsuarioControllerService {
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final SexoRepository sexoRepository;
    private final RolRepository rolRepository;

    @Autowired
    public UsuarioControllerServiceImplement(UsuarioService usuarioService, UsuarioRepository usuarioRepository,SexoRepository sexoRepository, RolRepository rolRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.sexoRepository = sexoRepository;
        this.rolRepository = rolRepository;
    }
    @Override
    public ResponseEntity<RespuestaEstandar> insertarUsuario(UsuarioDtoRecibir usuario) throws Exception {
        PaisEstadoDtoID paisEstadoDtoID = ((PaisEstadoDtoID) APIGeografia.comprobarPaisEstadoID(new PaisEstadoDtoNombre(usuario.pais(), usuario.estado())).getRespuesta());
        Rol rol = rolRepository.findByNombreEquals(usuario.nombre());
        Sexo sexo = sexoRepository.findByNombreEquals(usuario.sexo());
        Usuario user = new Usuario(usuario, paisEstadoDtoID, rol, sexo);
        usuarioService.insertarUsuario(user);
        return ResponseEntity.ok(new RespuestaEstandar("Usuario insertado"));
    }

    @Override
    public ResponseEntity<RespuestaEstandar> modificarUsuario(UsuarioDtoRecibir usuario, Long id) throws Exception {
        PaisEstadoDtoID paisEstadoDtoID = ((PaisEstadoDtoID) APIGeografia.comprobarPaisEstadoID(new PaisEstadoDtoNombre(usuario.pais(), usuario.estado())).getRespuesta());
        Rol rol = rolRepository.findByNombreEquals(usuario.nombre());
        Sexo sexo = sexoRepository.findByNombreEquals(usuario.sexo());
        Usuario user = new Usuario(id,usuario, paisEstadoDtoID, rol, sexo);
        usuarioService.actualizarUsuario(user);
        return ResponseEntity.ok(new RespuestaEstandar("Usuario modificado"));
    }

    @Override
    public ResponseEntity<RespuestaEstandar> eliminarUsuario(Long id) throws Exception {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok(new RespuestaEstandar("Usuario eliminado"));
    }

    @Override
    public ResponseEntity<RespuestaEstandar> obtenerUsuario(Long id) throws SearchException {
        Usuario user = usuarioService.getUsuario(id);
        return ResponseEntity.ok(new RespuestaEstandar(user));
    }

    @Override
    public ResponseEntity<RespuestaEstandar> obtenerUsuarios() throws SearchException {
        List<UsuarioDtoRecibir> listadoUsuarios = (List<UsuarioDtoRecibir>) usuarioRepository.findAll().stream().map(usuario -> {
            try {
                PaisEstadoDtoNombre paisEstadoDtoNombre = ((PaisEstadoDtoNombre) APIGeografia.comprobarPaisEstadoNombre(usuario.getIdPais(),usuario.getIdEstado()).getRespuesta());
                return UsuarioDtoEnviar.obtenerUsuarioDtoEnviar(usuario, paisEstadoDtoNombre.nombrePais(), paisEstadoDtoNombre.nombreEstado());
            } catch (Exception e) {
                return UsuarioDtoEnviar.obtenerUsuarioDtoEnviarNull();
            }
        });
        if (listadoUsuarios.stream().anyMatch(usuarioDtoRecibir -> usuarioDtoRecibir.nombre() == null))
            throw new SearchException("Usuarios encontrados con errores consulte a su administrador del sistema");
        return ResponseEntity.ok(new RespuestaEstandar(listadoUsuarios));
    }
}
