package cu.edu.cujae.gestion.postgresql.usuarios.core.service.implement;

import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.SearchException;
import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Usuario;
import cu.edu.cujae.gestion.postgresql.usuarios.core.repository.UsuarioRepository;
import cu.edu.cujae.gestion.postgresql.usuarios.core.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImplement implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioServiceImplement(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void insertarUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) throws SearchException {
        if (usuarioRepository.existsById(usuario.getIdUsuario())) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            usuarioRepository.save(usuario);
        }
        throw new SearchException("No se ha encontrado al usuario especificado");
    }

    @Override
    public void eliminarUsuario(Long idUsuario) throws SearchException {
        if (usuarioRepository.existsById(idUsuario)) {
            usuarioRepository.deleteById(idUsuario);
        }
        throw new SearchException("No se ha encontrado al usuario especificado");
    }

    @Override
    public Usuario getUsuario(Long idUsuario) throws SearchException {
        if (usuarioRepository.existsById(idUsuario)){
            return usuarioRepository.getReferenceById(idUsuario);
        }
        throw new SearchException("No se ha encontrado al usuario especificado");
    }
}
