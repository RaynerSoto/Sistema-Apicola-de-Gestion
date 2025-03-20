package cu.edu.cujae.gestion.postgresql.geografia.core.dto.apiGeografica;

import cu.edu.cujae.gestion.postgresql.estado.core.dto.apiGeografica.GeonamesDto;

import java.util.List;

public record APIGeoEstadoDto(int totalResultsCount, List<GeonamesDto> geonames) {
}
