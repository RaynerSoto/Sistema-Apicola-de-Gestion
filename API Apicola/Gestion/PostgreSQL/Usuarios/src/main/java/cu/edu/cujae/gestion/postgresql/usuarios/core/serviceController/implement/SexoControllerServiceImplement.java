package cu.edu.cujae.gestion.postgresql.usuarios.core.serviceController.implement;

import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.sexo.SexoDto;
import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.SearchException;
import cu.edu.cujae.gestion.postgresql.usuarios.core.repository.SexoRepository;
import cu.edu.cujae.gestion.postgresql.usuarios.core.service.SexoService;
import cu.edu.cujae.gestion.postgresql.usuarios.core.serviceController.SexoControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SexoControllerServiceImplement implements SexoControllerService {
    private final SexoService sexoService;
    private final SexoRepository sexoRepository;

    @Autowired
    public SexoControllerServiceImplement(SexoService sexoService, SexoRepository sexoRepository) {
        this.sexoService = sexoService;
        this.sexoRepository = sexoRepository;
    }

    @Override
    public ResponseEntity<RespuestaEstandar> listadoSexos() throws SearchException {
        try {
            return ResponseEntity.ok(new RespuestaEstandar(sexoRepository.findAll().stream().map(sexo -> SexoDto.toDto(sexo)).toList()));
        } catch (Exception e) {
            throw new SearchException("No se pudo acceder al listado de sexos. Compruebe con el servicio técnico");
        }
    }

    @Override
    public ResponseEntity<RespuestaEstandar> listadoSexosNombre() throws SearchException {
        try {
            return ResponseEntity.ok(new RespuestaEstandar(sexoService.listadoSexos()));
        } catch (Exception e) {
            throw new SearchException("No se pudo acceder al listado de nombres de sexos. Compruebe con el servicio técnico");
        }
    }
}
