package cu.edu.cujae.gestion.postgresql.geografia.core.service.implement;
import cu.edu.cujae.gestion.postgresql.geografia.core.model.Municipio;
import cu.edu.cujae.gestion.postgresql.geografia.core.repository.MunicipioRepository;
import cu.edu.cujae.gestion.postgresql.geografia.core.service.MunicipioService;
import cu.edu.cujae.gestion.postgresql.geografia.utils.Validacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio MunicipioService.
 * Proporciona métodos para insertar un municipio en la base de datos.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
@Service
@Deprecated
public class MunicipioServiceImplement implements MunicipioService {

    private final MunicipioRepository municipioRepository;

    @Autowired
    public MunicipioServiceImplement(MunicipioRepository municipioRepository) {
        this.municipioRepository = municipioRepository;
    }

    @Override
    public void insertarMunicipio(Municipio municipio) {
        municipioRepository.save(municipio);
    }
}
