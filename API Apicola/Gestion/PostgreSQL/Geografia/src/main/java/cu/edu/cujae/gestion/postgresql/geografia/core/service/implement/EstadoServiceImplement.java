package cu.edu.cujae.gestion.postgresql.geografia.core.service.implement;
import cu.edu.cujae.gestion.postgresql.geografia.core.model.Estado;
import cu.edu.cujae.gestion.postgresql.geografia.core.repository.EstadoRepository;
import cu.edu.cujae.gestion.postgresql.geografia.core.service.EstadoService;
import cu.edu.cujae.gestion.postgresql.geografia.core.utils.Validacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoServiceImplement implements EstadoService {

    private final EstadoRepository estadoRepository;

    @Autowired
    public EstadoServiceImplement(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public void insertarEstado(Estado estado) {
        Validacion.validarElemento(estado);
        estadoRepository.save(estado);
    }
}
