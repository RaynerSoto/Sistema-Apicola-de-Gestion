package cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.continente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContinenteDto{
    private Long idContinente;
    private String nombre;
}
