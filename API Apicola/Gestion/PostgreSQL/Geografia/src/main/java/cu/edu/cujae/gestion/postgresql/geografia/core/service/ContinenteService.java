package cu.edu.cujae.gestion.postgresql.geografia.core.service;

import cu.edu.cujae.gestion.postgresql.geografia.core.dto.continenteDto.ContinentesDto;
import cu.edu.cujae.gestion.postgresql.geografia.core.exception.SearchException;

import java.util.List;

public interface ContinenteService {
    List<ContinentesDto> listarContinentes() throws SearchException;
}
