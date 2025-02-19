package cu.edu.cujae.gestion.postgresql.estado.core.dto.apiGeografica;

import java.util.List;

public record APIGeoEstadoDto(int totalResultsCount, List<GeonamesDto> geonames) {
}
