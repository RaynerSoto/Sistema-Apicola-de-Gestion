package cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.apiGeografica;

import cu.edu.cujae.gestion.postgresql.microservicepais.core.utils.APITraduction;

import java.io.IOException;

public record APIGeoDto(NameDto name, String region, Double area, Long population, String cca3) {

    public APIGeoDto toEspañol() throws IOException, InterruptedException {
        return new APIGeoDto(
                new NameDto(APITraduction.traduccionENES(this.name().common()),APITraduction.traduccionENES(this.name().official())),
                APITraduction.traduccionENES(this.region),
                this.area,
                this.population,
                this.cca3
                );
    }
}
