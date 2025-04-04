package cu.edu.cujae.gestion.postgresql.usuarios.core.service.implement;

import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Rol;
import cu.edu.cujae.gestion.postgresql.usuarios.core.repository.RolRepositiry;
import cu.edu.cujae.gestion.postgresql.usuarios.core.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImplement implements RolService {
    private RolRepositiry rolRepositiry;

    @Autowired
    public RolServiceImplement(RolRepositiry rolRepositiry) {
        this.rolRepositiry = rolRepositiry;
    }

    @Override
    public List<String> listadoRoles() {
        return rolRepositiry.findAll().stream().map(Rol::getRol_nombre).toList();
    }
}
