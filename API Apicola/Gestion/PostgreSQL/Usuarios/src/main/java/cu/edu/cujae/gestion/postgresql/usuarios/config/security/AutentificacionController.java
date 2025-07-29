package cu.edu.cujae.gestion.postgresql.usuarios.config.security;

import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.login.UsuarioLogin;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.SearchException;
import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Usuario;
import cu.edu.cujae.gestion.postgresql.usuarios.core.repository.UsuarioRepository;
import cu.edu.cujae.gestion.postgresql.usuarios.core.service.UsuarioService;
import cu.edu.cujae.gestion.postgresql.usuarios.utils.TokenUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/login")
@Tag(name = "Controlador de la autentificación",description = "Determina el funcionamiento de la seguridad")
public class AutentificacionController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final TokenService tokenService;
    private final TokenUtils tokenUtils;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public AutentificacionController(UsuarioRepository usuarioRepository, TokenUtils tokenUtils, TokenService tokenService, UsuarioService usuarioService, AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.tokenUtils = tokenUtils;
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = "/")
    @Operation(summary = "Encargado de autentificar el usuario")
    public ResponseEntity<RespuestaEstandar> autenticacionUsuario(@RequestBody UsuarioLogin usuarioLoginDto, HttpServletRequest request) throws Exception {
        Usuario usuario = usuarioService.obtenerUsuarioPorUsernameAndPassword(usuarioLoginDto.username(), usuarioLoginDto.password());
        if (usuario.getIsActivo() == false)
            throw new Exception("No es posible autentificar al usuario por no estar activo");
        usuario = new Usuario(usuario, usuarioLoginDto.password());
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(usuario.getUsername(),usuario.getPassword());;
        var usuarioAutentificado = authenticationManager.authenticate(authenticationToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutentificado.getPrincipal());
        return ResponseEntity.ok(new RespuestaEstandar(JWTtoken));
    }
    /*
    @PostMapping("/validarToken")
    @Operation(summary = "Encargado de autentificar a un usuario según su token")
    public ResponseEntity<?> autenticacionUsuario(@RequestBody TokenDto tokenDto){
        try {
            if (tokenDto.getToken().equals(null) || tokenDto.getToken().equals("")) {
                throw new Exception("Token nulo o inválido");
            }
            String nombre = tokenService.getSubjetc(tokenDto.getToken());
            Optional<Usuario> user = Optional.ofNullable(usuarioService.usuarioActivoUsername(nombre).orElseThrow(
                    () -> new RuntimeException("No se encontró al usuario del token")
            ));
            return ResponseEntity.ok(new UsuarioDto(user));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/userdetails")
    @Operation(summary = "Encargado de retornar el UserDetails")
    public ResponseEntity<?> retornoUserDetails(@RequestBody TokenDto tokenDto){
        try{
            if (tokenDto.getToken().equals(null) || tokenDto.getToken().equals("")) {
                throw new Exception("Token nulo o inválido");
            }
            String nombre = tokenService.getSubjetc(tokenDto.getToken());
            if (nombre.isEmpty() || nombre.isBlank())
                return ResponseEntity.badRequest().body("Usuario no valido");
            UserDetails usuario = usuarioRepository.findByUsernameEqualsIgnoreCase(nombre);
            if (usuario != null)
                return ResponseEntity.ok(usuario);
            return ResponseEntity.badRequest().body("No se encontró el usuario");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }*/
}
