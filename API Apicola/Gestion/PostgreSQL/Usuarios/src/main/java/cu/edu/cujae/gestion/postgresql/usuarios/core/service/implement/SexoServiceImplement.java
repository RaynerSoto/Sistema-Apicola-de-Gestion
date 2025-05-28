package cu.edu.cujae.gestion.postgresql.usuarios.core.service.implement;

import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Sexo;
import cu.edu.cujae.gestion.postgresql.usuarios.core.repository.SexoRepository;
import cu.edu.cujae.gestion.postgresql.usuarios.core.service.SexoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de gestión de sexos.
 * Proporciona métodos para listar todos los sexos disponibles en el sistema.
 */
@Service
public class SexoServiceImplement implements SexoService {
    private SexoRepository sexoRepository;

    @Autowired
    public SexoServiceImplement(SexoRepository sexoRepository) {
        this.sexoRepository = sexoRepository;
    }

    @Override
    /**
     * Lista todos los sexos disponibles en el sistema.
     * @return List<String> - Lista de nombres de sexos.
     * * Esta función utiliza el repositorio de sexos para obtener todos los registros
     */
    public List<String> listadoSexos() {
        return sexoRepository.findAll().stream().map(Sexo::getNombre).collect(Collectors.toList());
    }
}
