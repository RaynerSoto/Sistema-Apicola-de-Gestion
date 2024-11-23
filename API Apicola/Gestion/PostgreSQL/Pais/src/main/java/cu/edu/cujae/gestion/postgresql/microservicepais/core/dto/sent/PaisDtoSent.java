package cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.sent;

import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.recibir.PaisDtoReci;

public record PaisDtoSent(Long id) implements PaisDtoReci {
}
