package cu.edu.cujae.gestion.postgresql.municio.core.dto.apiGeografica;

import java.util.List;

public record APIGeoMunicipioDto(int totalResultsCount, List<GeonamesDto> geonames) {
}
