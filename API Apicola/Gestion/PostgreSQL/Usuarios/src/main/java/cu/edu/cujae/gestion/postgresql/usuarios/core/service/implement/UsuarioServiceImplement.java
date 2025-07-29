package cu.edu.cujae.gestion.postgresql.usuarios.core.service.implement;

import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.SearchException;
import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Usuario;
import cu.edu.cujae.gestion.postgresql.usuarios.core.repository.UsuarioRepository;
import cu.edu.cujae.gestion.postgresql.usuarios.core.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * Implementación del servicio de usuario.
 * Esta clase maneja las operaciones relacionadas con los usuarios, como insertar, actualizar,
 * eliminar y obtener usuarios del sistema.
 */
@Service
public class UsuarioServiceImplement implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    private final Logger logger = Logger.getLogger(UsuarioServiceImplement.class.getName());

    @Autowired
    public UsuarioServiceImplement(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Inserta un nuevo usuario en el sistema.
     * @param usuario
     * El usuario a insertar, debe contener todos sus datos validados de manera correcta.
     * El password del usuario se codifica antes de ser guardado en la base de datos.
     * @return void
     * Esta función no devuelve ningún valor, simplemente guarda el usuario en la base de datos.
     */
    @Override
    public void insertarUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
    }

    /**
     * Lista todos los usuarios del sistema.
     * @return List<Usuario>
     * Devuelve una lista con todos los usuarios registrados en el sistema.
     * @throws SearchException
     * Si el usuario no existe, se lanza una excepción.
     * Esta función utiliza el repositorio de usuarios para obtener todos los registros.
     */
    @Override
    public void actualizarUsuario(Usuario usuario) throws SearchException {
        if (!usuarioRepository.existsByIdUsuario(usuario.getIdUsuario()))
            throw new SearchException("No se ha encontrado al usuario especificado");
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
    }

    /**
     * Elimina un usuario del sistema.
     * @param idUsuario
     * El identificador del usuario a eliminar.
     * @throws SearchException
     * Si el usuario no existe, se lanza una excepción.
     * Esta función utiliza el repositorio de usuarios para eliminar el registro.
     */
    @Override
    public void eliminarUsuario(Long idUsuario) throws SearchException {
        if (!usuarioRepository.existsByIdUsuario(idUsuario))
            throw new SearchException("No se ha encontrado al usuario especificado");
        usuarioRepository.deleteById(idUsuario);
    }

    /**
     * Obtiene un usuario del sistema.
     * @param idUsuario
     * El identificador del usuario a buscar.
     * @return Usuario
     * Devuelve el usuario encontrado.
     * @throws SearchException
     * Si el usuario no existe, se lanza una excepción.
     * Esta función utiliza el repositorio de usuarios para obtener el registro.
     */
    @Override
    public Usuario getUsuario(Long idUsuario) throws SearchException {
        if (usuarioRepository.existsById(idUsuario))
            throw new SearchException("No se ha encontrado al usuario especificado");
        return usuarioRepository.getReferenceById(idUsuario);
    }

    @Override
    public Usuario obtenerUsuarioPorUsernameAndPassword(String username, String password) throws SearchException {
        Usuario usuario = usuarioRepository.findByUsernameEqualsIgnoreCase(username).orElseThrow(() -> new SearchException("Usuario no encontrado con el username: " + username));
        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new SearchException("Credenciales incorrectas " + username);
        }
        return usuario;
    }
}
