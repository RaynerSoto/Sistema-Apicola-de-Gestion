package cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.recibir;

import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.sent.PaisDtoSent;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.mapping.Pais;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.utils.Validacion;
import org.locationtech.jts.geom.Geometry;

public record PaisDtoReci(String nombre, String sigla, Long poblacion, Geometry geometry, String nombre_continente, Double areaKM2, Geometry centroide) {
    public static PaisDtoReci convertirAPaisDtoReci(Pais pais) {
        return new PaisDtoReci(pais.getNombre(),pais.getSigla(),pais.getPoblacion(),pais.getGeometry(),pais.getNombreContinente(),pais.getAreaKM2(),pais.getCentroide());
    }
}
