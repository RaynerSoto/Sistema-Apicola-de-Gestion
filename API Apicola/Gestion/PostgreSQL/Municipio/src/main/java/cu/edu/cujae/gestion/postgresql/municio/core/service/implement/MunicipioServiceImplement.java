package cu.edu.cujae.gestion.postgresql.municio.core.service.implement;
import cu.edu.cujae.gestion.postgresql.municio.core.model.Municipio;
import cu.edu.cujae.gestion.postgresql.municio.core.repository.MunicipioRepository;
import cu.edu.cujae.gestion.postgresql.municio.core.service.MunicipioService;
import cu.edu.cujae.gestion.postgresql.municio.core.utils.Validacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MunicipioServiceImplement implements MunicipioService {

    private final MunicipioRepository municipioRepository;

    @Autowired
    public MunicipioServiceImplement(MunicipioRepository municipioRepository) {
        this.municipioRepository = municipioRepository;
    }

    @Override
    public void insertarMunicipio(Municipio municipio) {
        Validacion.validarElemento(municipio);
        municipioRepository.save(municipio);
    }
}
