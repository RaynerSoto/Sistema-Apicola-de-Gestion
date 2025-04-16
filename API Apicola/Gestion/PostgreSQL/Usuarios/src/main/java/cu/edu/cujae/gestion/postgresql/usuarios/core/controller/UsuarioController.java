package cu.edu.cujae.gestion.postgresql.usuarios.core.controller;

import cu.edu.cujae.gestion.postgresql.usuarios.core.conexion.microservicios.APIGeografia;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.paisEstado.PaisEstadoDtoComprobar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.usuario.UsuarioDto;
import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.APIException;
import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.SearchException;
import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Rol;
import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Sexo;
import cu.edu.cujae.gestion.postgresql.usuarios.core.repository.RolRepository;
import cu.edu.cujae.gestion.postgresql.usuarios.core.repository.SexoRepository;
import cu.edu.cujae.gestion.postgresql.usuarios.core.repository.UsuarioRepository;
import cu.edu.cujae.gestion.postgresql.usuarios.core.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        if (!(Boolean) APIGeografia.comprobarPaisEstado(new PaisEstadoDtoComprobar(usuario.pais(), usuario.estado())).getRespuesta()){
            throw new SearchException("No se ha podido comprobar que el país y el estado existan o que pertenezcan el uno al otro");
        }

        Rol rol = rolRepository.findByRol_nombreEquals(usuario.nombre());
        Sexo sexo = sexoRepository.findByNombreEquals(usuario.sexo());
        return ResponseEntity.ok(new RespuestaEstandar("Usuario insertado"));
    }
}
