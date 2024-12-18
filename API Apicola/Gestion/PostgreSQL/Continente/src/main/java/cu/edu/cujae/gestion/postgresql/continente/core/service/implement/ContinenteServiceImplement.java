package cu.edu.cujae.gestion.postgresql.continente.core.service.implement;

import cu.edu.cujae.gestion.postgresql.continente.core.dto.standar.ContinenteDto;
import cu.edu.cujae.gestion.postgresql.continente.core.model.Continente;
import cu.edu.cujae.gestion.postgresql.continente.core.repository.ContinenteRepository;
import cu.edu.cujae.gestion.postgresql.continente.core.service.ContinenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContinenteServiceImplement implements ContinenteService {
    private ContinenteRepository continenteRepository;

    @Autowired
    public ContinenteServiceImplement(ContinenteRepository continenteRepository) {
        this.continenteRepository = continenteRepository;
    }

    @Override
    public ContinenteDto isCorrectContinente(String nombreContinente) {
        Optional<Continente> continente = continenteRepository.findByNombreEqualsIgnoreCase(nombreContinente);
        if (continente.isPresent()) {
            return new ContinenteDto(continente.get().getIdContinente(), continente.get().getNombre());
        }
        throw new RuntimeException("No hay un continente con el nombre " + nombreContinente);
    }

    @Override
    public List<ContinenteDto> getAllContinentes() {
        return continenteRepository.findAll().stream().
                map(continente -> new ContinenteDto(continente.getIdContinente(), continente.getNombre())).toList();
    }
}
