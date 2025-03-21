package cu.edu.cujae.gestion.postgresql.geografia.core.dto.apiGeografica;
import java.util.List;

public record APIGeoEstadoDto(int totalResultsCount, List<GeonamesDto> geonames) {
}
