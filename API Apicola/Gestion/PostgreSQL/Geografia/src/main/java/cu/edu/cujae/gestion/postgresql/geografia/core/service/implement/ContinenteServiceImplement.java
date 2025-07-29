package cu.edu.cujae.gestion.postgresql.geografia.core.service.implement;

import cu.edu.cujae.gestion.postgresql.geografia.core.dto.continenteDto.ContinentesDto;
import cu.edu.cujae.gestion.postgresql.geografia.core.exception.SearchException;
import cu.edu.cujae.gestion.postgresql.geografia.core.model.Continente;
import cu.edu.cujae.gestion.postgresql.geografia.core.repository.ContinenteRepository;
import cu.edu.cujae.gestion.postgresql.geografia.core.service.ContinenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio ContinenteService.
 * Proporciona métodos para listar todos los continentes y buscar un continente por su nombre.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
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
        return continenteRepository.findByNombreEqualsIgnoreCase(nombre).orElseThrow(() -> new SearchException("El continente con nombre: " + nombre + " no existe"));
    }
}
