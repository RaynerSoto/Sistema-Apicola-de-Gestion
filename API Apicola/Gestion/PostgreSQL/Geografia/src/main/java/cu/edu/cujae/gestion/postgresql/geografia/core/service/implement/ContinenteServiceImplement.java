package cu.edu.cujae.gestion.postgresql.geografia.core.service.implement;

import cu.edu.cujae.gestion.postgresql.geografia.core.dto.continenteDto.ContinentesDto;
import cu.edu.cujae.gestion.postgresql.geografia.core.exception.SearchException;
import cu.edu.cujae.gestion.postgresql.geografia.core.model.Continente;
import cu.edu.cujae.gestion.postgresql.geografia.core.repository.ContinenteRepository;
import cu.edu.cujae.gestion.postgresql.geografia.core.service.ContinenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContinenteServiceImplement implements ContinenteService {

    private ContinenteRepository continenteRepository;

    @Autowired
    public ContinenteServiceImplement(ContinenteRepository continenteRepository) {
        this.continenteRepository = continenteRepository;
    }

    @Override
    public List<ContinentesDto> listarContinentes() throws SearchException {
        return continenteRepository.findAll().stream().map(ContinentesDto::convetirContinenteAContinenteDto).toList();
    }

    @Override
    public Continente buscarContinente(String nombre) throws SearchException {
        try {
            return continenteRepository.findByNombreEqualsIgnoreCase(nombre).get();
        } catch (Exception e) {
            throw new SearchException("No se pudo obtener dicho continente");
        }
    }
}
