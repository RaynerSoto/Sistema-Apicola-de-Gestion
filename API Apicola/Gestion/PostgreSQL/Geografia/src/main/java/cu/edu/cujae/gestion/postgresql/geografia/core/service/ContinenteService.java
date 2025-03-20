package cu.edu.cujae.gestion.postgresql.geografia.core.service;

import cu.edu.cujae.gestion.postgresql.continente.core.dto.standar.ContinenteDto;

import java.util.List;

public interface ContinenteService {
    public ContinenteDto isCorrectContinente(String nombreContinente);
    public List<ContinenteDto> getAllContinentes();
}
