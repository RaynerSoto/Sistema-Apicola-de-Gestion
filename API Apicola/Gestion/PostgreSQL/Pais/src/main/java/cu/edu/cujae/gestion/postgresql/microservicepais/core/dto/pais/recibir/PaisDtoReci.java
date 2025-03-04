package cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.pais.recibir;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.model.Pais;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

public record PaisDtoReci(String nombre, String sigla, Long poblacion, Polygon geometry, String nombre_continente, Double areaKM2, Point centroide) {
    public static PaisDtoReci convertirAPaisDtoReci(Pais pais) {
        return new PaisDtoReci(pais.getNombre(),pais.getSigla(),pais.getPoblacion(),pais.getGeometry(),pais.getNombreContinente(),pais.getAreaKM2(),pais.getCentroide());
    }
}
