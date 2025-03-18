package cu.edu.cujae.gestion.postgresql.municio.core.dto.apiGeografica;

import java.util.List;

public record APIGeoEstadoDto(int totalResultsCount, List<GeonamesDto> geonames) {
}
