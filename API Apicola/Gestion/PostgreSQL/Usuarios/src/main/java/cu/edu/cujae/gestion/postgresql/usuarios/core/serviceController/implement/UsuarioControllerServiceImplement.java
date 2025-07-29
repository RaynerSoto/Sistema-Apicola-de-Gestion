package cu.edu.cujae.gestion.postgresql.usuarios.core.serviceController.implement;

import com.google.gson.Gson;
import cu.edu.cujae.gestion.postgresql.usuarios.core.conexion.microservicios.APIGeografia;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.paisEstado.PaisEstadoDtoID;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.paisEstado.PaisEstadoDtoNombre;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.usuario.UsuarioDtoEnviar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.usuario.UsuarioDtoRecibir;
import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.APIException;
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

import java.io.IOException;
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
        Gson gson = new Gson();
        PaisEstadoDtoID paisEstadoDtoID = gson.fromJson(gson.toJson(APIGeografia.comprobarPaisEstadoID(new PaisEstadoDtoNombre(usuario.pais(), usuario.estado())).getRespuesta()),PaisEstadoDtoID.class);
        Rol rol = rolRepository.findByNombreEquals(usuario.rol()).orElseThrow(() -> new SearchException("Rol no encontrado: " + usuario.rol()));
        Sexo sexo = sexoRepository.findByNombreEquals(usuario.sexo()).orElseThrow(() -> new SearchException("Sexo no encontrado: " + usuario.sexo()));
        Usuario user = new Usuario(usuario, paisEstadoDtoID, rol, sexo);
        usuarioService.insertarUsuario(user);
        return ResponseEntity.ok(new RespuestaEstandar("Usuario insertado"));
    }

    @Override
    public ResponseEntity<RespuestaEstandar> modificarUsuario(UsuarioDtoRecibir usuario, Long id) throws Exception {
        Gson gson = new Gson();
        PaisEstadoDtoID paisEstadoDtoID = gson.fromJson(gson.toJson(APIGeografia.comprobarPaisEstadoID(new PaisEstadoDtoNombre(usuario.pais(), usuario.estado())).getRespuesta()),PaisEstadoDtoID.class);
        Rol rol = rolRepository.findByNombreEquals(usuario.rol()).orElseThrow(() -> new SearchException("Rol no encontrado: " + usuario.rol()));
        Sexo sexo = sexoRepository.findByNombreEquals(usuario.sexo()).orElseThrow(() -> new SearchException("Sexo no encontrado: " + usuario.sexo()));
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
    public ResponseEntity<RespuestaEstandar> obtenerUsuario(Long id) throws SearchException, APIException, IOException, InterruptedException {
        Usuario user = usuarioService.getUsuario(id);
        PaisEstadoDtoNombre paisEstadoDtoNombre = new Gson().fromJson(new Gson().toJson(APIGeografia.comprobarPaisEstadoNombre(user.getIdPais(),user.getIdEstado()).getRespuesta()),PaisEstadoDtoNombre.class);
        UsuarioDtoEnviar usuario = UsuarioDtoEnviar.obtenerdeUsuario(user, paisEstadoDtoNombre.nombreEstado(), paisEstadoDtoNombre.nombrePais());
        return ResponseEntity.ok(new RespuestaEstandar(usuario));
    }

    @Override
    public ResponseEntity<RespuestaEstandar> obtenerUsuarios() throws SearchException {
        List<UsuarioDtoEnviar> listadoUsuarios = usuarioRepository.findAll().stream().map(usuario -> {
            try {
                PaisEstadoDtoNombre paisEstadoDtoNombre = new Gson().fromJson(new Gson().toJson(APIGeografia.comprobarPaisEstadoNombre(usuario.getIdPais(),usuario.getIdEstado()).getRespuesta()),PaisEstadoDtoNombre.class);
                return UsuarioDtoEnviar.obtenerUsuarioDtoEnviar(usuario, paisEstadoDtoNombre.nombrePais(), paisEstadoDtoNombre.nombreEstado());
            } catch (Exception e) {
                return UsuarioDtoEnviar.obtenerUsuarioDtoEnviarNull();
            }
        }).toList();
        if (listadoUsuarios.stream().anyMatch(usuarioDtoRecibir -> usuarioDtoRecibir.nombre() == null))
            throw new SearchException("Usuarios encontrados con errores consulte a su administrador del sistema");
        return ResponseEntity.ok(new RespuestaEstandar(listadoUsuarios));
    }
}
