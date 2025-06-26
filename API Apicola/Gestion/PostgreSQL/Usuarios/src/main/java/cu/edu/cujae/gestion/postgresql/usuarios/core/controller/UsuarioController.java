package cu.edu.cujae.gestion.postgresql.usuarios.core.controller;

import cu.edu.cujae.gestion.postgresql.usuarios.core.conexion.microservicios.APIGeografia;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.paisEstado.PaisEstadoDtoComprobar;
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
import cu.edu.cujae.gestion.postgresql.usuarios.utils.Validacion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * Controlador para manejar las operaciones relacionadas con los usuarios.
 * Proporciona endpoints para insertar, modificar, eliminar y obtener usuarios,
 * así como para listar todos los usuarios.
 * @author Rayner Alejandro Soto Martínez
 * @version 0.3.0
 */
@RestController
@RequestMapping("/api/v1/gestion/1/usuario")
@Tag(name = "Controlador de los usuarios")
public class UsuarioController {
    private final UsuarioControllerService usuarioControllerService;

    @Autowired
    public UsuarioController(UsuarioControllerService usuarioControllerService) {
        this.usuarioControllerService = usuarioControllerService;
    }

    @PostMapping("/")
    @Operation(summary = "Insertar un nuevo usuario",
            description = "Permite insertar un nuevo usuario en el sistema. " +
                    "Requiere los datos del usuario en el cuerpo de la solicitud.")
    public ResponseEntity<RespuestaEstandar> insertarUsuario(@RequestBody @Valid UsuarioDtoRecibir usuario) throws Exception {
        Validacion.validarElemento(usuario);
        return usuarioControllerService.insertarUsuario(usuario);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar un usuario existente",
            description = "Permite modificar un usuario existente en el sistema. " +
                    "Requiere los datos actualizados del usuario en el cuerpo de la solicitud y el ID del usuario a modificar.")
    public ResponseEntity<RespuestaEstandar> modificarUsuario(@RequestBody @Valid UsuarioDtoRecibir usuario,
                                                              @PathVariable @Min(value = 1,message = "El valor del ID del usuario debe ser mayor o igual a 1") Long id) throws Exception {
        Validacion.validarElemento(usuario);
        return usuarioControllerService.modificarUsuario(usuario,id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario",
            description = "Permite eliminar un usuario del sistema. " +
                    "Requiere el ID del usuario a eliminar en la ruta.")
    public ResponseEntity<RespuestaEstandar> eliminarUsuario(@PathVariable @Min(value = 1,message = "El valor del ID del usuario debe ser mayor o igual a 1") Long id) throws Exception {
        return usuarioControllerService.eliminarUsuario(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un usuario por ID",
            description = "Permite obtener los detalles de un usuario específico por su ID. " +
                    "Requiere el ID del usuario en la ruta.")
    public ResponseEntity<RespuestaEstandar> obtenerUsuario(@PathVariable @Min(value = 1,message = "El valor del ID del usuario debe ser mayor o igual a 1") Long id) throws Exception {
        return usuarioControllerService.obtenerUsuario(id);
    }

    @GetMapping("/listar")
    @Operation(summary = "Obtener todos los usuarios",
            description = "Permite obtener una lista de todos los usuarios registrados en el sistema.")
    public ResponseEntity<RespuestaEstandar> obtenerUsuarios() throws SearchException, APIException, IOException, InterruptedException {
        return usuarioControllerService.obtenerUsuarios();
    }
}
