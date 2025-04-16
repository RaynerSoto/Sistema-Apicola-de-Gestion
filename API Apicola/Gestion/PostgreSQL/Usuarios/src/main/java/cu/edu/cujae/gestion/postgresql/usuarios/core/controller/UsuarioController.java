package cu.edu.cujae.gestion.postgresql.usuarios.core.controller;

import cu.edu.cujae.gestion.postgresql.usuarios.core.conexion.microservicios.APIGeografia;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.paisEstado.PaisEstadoDtoComprobar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.paisEstado.PaisEstadoDtoID;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.usuario.UsuarioDto;
import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.APIException;
import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.SearchException;
import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Rol;
import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Sexo;
import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Usuario;
import cu.edu.cujae.gestion.postgresql.usuarios.core.repository.RolRepository;
import cu.edu.cujae.gestion.postgresql.usuarios.core.repository.SexoRepository;
import cu.edu.cujae.gestion.postgresql.usuarios.core.repository.UsuarioRepository;
import cu.edu.cujae.gestion.postgresql.usuarios.core.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/gestion/1/usuario")
@Tag(name = "Controlador de los usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final SexoRepository sexoRepository;
    private final RolRepository rolRepository;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository, SexoRepository sexoRepository, RolRepository rolRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.sexoRepository = sexoRepository;
        this.rolRepository = rolRepository;
    }

    @PostMapping("/")
    public ResponseEntity<RespuestaEstandar> insertarUsuario(@RequestBody UsuarioDto usuario) throws SearchException, APIException, IOException, InterruptedException {
        PaisEstadoDtoID paisEstadoDtoID = ((PaisEstadoDtoID) APIGeografia.comprobarPaisEstado(new PaisEstadoDtoComprobar(usuario.pais(), usuario.estado())).getRespuesta());
        Rol rol = rolRepository.findByNombreEquals(usuario.nombre());
        Sexo sexo = sexoRepository.findByNombreEquals(usuario.sexo());
        Usuario user = new Usuario(usuario, paisEstadoDtoID, rol, sexo);
        usuarioService.insertarUsuario(user);
        return ResponseEntity.ok(new RespuestaEstandar("Usuario insertado"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaEstandar> modificarUsuario(@RequestBody UsuarioDto usuario, @PathVariable Long id) throws SearchException, APIException, IOException, InterruptedException {
        PaisEstadoDtoID paisEstadoDtoID = ((PaisEstadoDtoID) APIGeografia.comprobarPaisEstado(new PaisEstadoDtoComprobar(usuario.pais(), usuario.estado())).getRespuesta());
        Rol rol = rolRepository.findByNombreEquals(usuario.nombre());
        Sexo sexo = sexoRepository.findByNombreEquals(usuario.sexo());
        Usuario user = new Usuario(id,usuario, paisEstadoDtoID, rol, sexo);
        usuarioService.actualizarUsuario(user);
        return ResponseEntity.ok(new RespuestaEstandar("Usuario modificado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaEstandar> eliminarUsuario(@PathVariable Long id) throws SearchException, APIException, IOException, InterruptedException {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok(new RespuestaEstandar("Usuario eliminado"));
    }

    @GetMapping("/")
    public ResponseEntity<RespuestaEstandar> obtenerUsuario(@RequestParam Long id) throws SearchException, APIException, IOException, InterruptedException {
        Usuario user = usuarioService.getUsuario(id);
        return ResponseEntity.ok(new RespuestaEstandar(user));
    }
}
