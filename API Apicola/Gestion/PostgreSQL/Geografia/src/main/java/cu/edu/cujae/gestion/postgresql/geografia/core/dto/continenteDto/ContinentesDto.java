package cu.edu.cujae.gestion.postgresql.geografia.core.dto.continenteDto;

import cu.edu.cujae.gestion.postgresql.geografia.core.model.Continente;

public record ContinentesDto(Long id, String nombre) {
    public static ContinentesDto convetirContinenteAContinenteDto(Continente continente){
        return new ContinentesDto(continente.getIdContinente(),continente.getNombre());
    }
}
