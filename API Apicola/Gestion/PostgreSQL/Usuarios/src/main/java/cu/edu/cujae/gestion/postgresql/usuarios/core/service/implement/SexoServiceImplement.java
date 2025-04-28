package cu.edu.cujae.gestion.postgresql.usuarios.core.service.implement;

import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Sexo;
import cu.edu.cujae.gestion.postgresql.usuarios.core.repository.SexoRepository;
import cu.edu.cujae.gestion.postgresql.usuarios.core.service.SexoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SexoServiceImplement implements SexoService {
    private SexoRepository sexoRepository;

    @Autowired
    public SexoServiceImplement(SexoRepository sexoRepository) {
        this.sexoRepository = sexoRepository;
    }

    @Override
    public List<String> listadoSexos() {
        return sexoRepository.findAll().stream().map(Sexo::getNombre).collect(Collectors.toList());
    }
}
