package cu.edu.cujae.gestion.postgresql.geografia.core.service;

import cu.edu.cujae.gestion.postgresql.geografia.core.dto.continenteDto.ContinentesDto;
import cu.edu.cujae.gestion.postgresql.geografia.core.exception.SearchException;
import cu.edu.cujae.gestion.postgresql.geografia.core.model.Continente;

import java.util.List;

/**
 * Servicio para gestionar las operaciones CRUD de la entidad Continente.
 * Este servicio proporciona métodos para listar continentes y buscar un continente por su nombre.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
public interface ContinenteService {
    List<ContinentesDto> listarContinentes() throws SearchException;
    Continente buscarContinente(String nombre) throws SearchException;
}
