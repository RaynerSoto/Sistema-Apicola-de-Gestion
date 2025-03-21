package cu.edu.cujae.gestion.postgresql.geografia.core.service;

import cu.edu.cujae.gestion.postgresql.geografia.core.dto.continenteDto.ContinentesDto;
import cu.edu.cujae.gestion.postgresql.geografia.core.exception.SearchException;
import cu.edu.cujae.gestion.postgresql.geografia.core.model.Continente;

import java.util.List;

public interface ContinenteService {
    List<ContinentesDto> listarContinentes() throws SearchException;
    Continente buscarContinente(String nombre) throws SearchException;
}
