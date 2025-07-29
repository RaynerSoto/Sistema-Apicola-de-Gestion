package cu.edu.cujae.gestion.postgresql.usuarios.core.service.implement;

import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Rol;
import cu.edu.cujae.gestion.postgresql.usuarios.core.repository.RolRepository;
import cu.edu.cujae.gestion.postgresql.usuarios.core.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio de roles.
 * Proporciona métodos para listar todos los roles disponibles en el sistema.
 */
@Service
public class RolServiceImplement implements RolService {
    private RolRepository rolRepository;

    @Autowired
    public RolServiceImplement(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    /**
     * Lista todos los roles disponibles en el sistema.
     * @return List<String> - Lista de nombres de roles.
     * * Esta función utiliza el repositorio de roles para obtener todos los registros
     */
    @Override
    public List<String> listadoRoles() {
        return rolRepository.findAll().stream().map(Rol::getNombre).toList();
    }
}
