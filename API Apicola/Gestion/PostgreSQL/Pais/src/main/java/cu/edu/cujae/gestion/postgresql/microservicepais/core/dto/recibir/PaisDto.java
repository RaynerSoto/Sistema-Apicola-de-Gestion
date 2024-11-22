package cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.recibir;

import cu.edu.cujae.gestion.postgresql.microservicepais.core.utils.Validacion;
import org.locationtech.jts.geom.Geometry;

public record PaisDto(String nombre, String sigla, Long poblacion, Geometry geometry, String nombre_continente, String codigo, Double areaKM2, Geometry centroide) {
    public void validar() {
        Validacion.validarUnsupportedOperationException(this);
    }
}
