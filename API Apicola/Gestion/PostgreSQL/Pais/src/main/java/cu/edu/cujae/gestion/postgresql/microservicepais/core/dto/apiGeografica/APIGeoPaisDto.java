package cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.apiGeografica;

public record APIGeoPaisDto(NameDto name, SpaDto spa, String region, Double area, Long population, String cca2, String fifa) {
}
