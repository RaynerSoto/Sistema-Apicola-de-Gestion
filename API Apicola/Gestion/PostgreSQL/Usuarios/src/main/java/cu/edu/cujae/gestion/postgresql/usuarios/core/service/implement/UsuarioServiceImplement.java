package cu.edu.cujae.gestion.postgresql.usuarios.core.service.implement;

import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Usuario;
import cu.edu.cujae.gestion.postgresql.usuarios.core.repository.UsuarioRepository;
import cu.edu.cujae.gestion.postgresql.usuarios.core.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImplement implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImplement(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void insertarUsuario(Usuario usuario) {

    }

    @Override
    public void actualizarUsuario(Usuario usuario) {

    }

    @Override
    public void eliminarUsuario(Long idUsuario) {

    }

    @Override
    public Usuario getUsuario(Long idUsuario) {
        return null;
    }
}
