package cu.edu.cujae.gestion.postgresql.usuarios.core.serviceController.implement;

import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.rol.RolDto;
import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.SearchException;
import cu.edu.cujae.gestion.postgresql.usuarios.core.repository.RolRepository;
import cu.edu.cujae.gestion.postgresql.usuarios.core.service.RolService;
import cu.edu.cujae.gestion.postgresql.usuarios.core.serviceController.RolControllerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RolControllerServiceImplement implements RolControllerService {
    private final RolRepository rolRepository;
    private final RolService rolService;

    @Autowired
    public RolControllerServiceImplement(RolRepository rolRepository,RolService rolService) {
        this.rolRepository = rolRepository;
        this.rolService = rolService;
    }
    @Override
    public ResponseEntity<RespuestaEstandar> listadoRoles() throws SearchException {
        try {
            return ResponseEntity.ok(new RespuestaEstandar(rolRepository.findAll().stream().map(RolDto::toDto).toList()));
        } catch (Exception e) {
            throw new SearchException("No se pudo acceder al listado de roles. Compruebe con el servicio técnico");
        }
    }

    @Override
    public ResponseEntity<RespuestaEstandar> listadoRolesNombre() throws SearchException {
        try {
            return ResponseEntity.ok(new RespuestaEstandar(rolService.listadoRoles()));
        } catch (Exception e) {
            throw new SearchException("No se pudo acceder al listado de roles. Compruebe con el servicio técnico");
        }
    }
}
