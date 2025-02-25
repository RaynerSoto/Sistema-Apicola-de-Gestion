package cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.apiGeografica;

import cu.edu.cujae.gestion.postgresql.microservicepais.core.exception.APIException;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.utils.APITraduction;

import java.io.IOException;

public record APIGeoPaisDto(NameDto name, String region, Double area, Long population, String cca2, String fifa) {
    public APIGeoPaisDto toEspañol() throws IOException, InterruptedException, APIException {
        NameDto nameDto = new NameDto(APITraduction.traduccionENES(this.name.common()),this.name().official());
        return new APIGeoPaisDto(nameDto, APITraduction.traduccionENES(this.region)
                ,this.area
                ,this.population
                ,this.cca2
                ,this.fifa);
    }
}
