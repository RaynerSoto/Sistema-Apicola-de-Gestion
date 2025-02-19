package cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.apiGeografica;

public record APIGeoDto(NameDto name,SpaDto spa, String region, Double area, Long population, String cca3,String fifa) {
}
