package cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.sent;

import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.recibir.PaisDtoReci;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.mapping.Pais;
import org.locationtech.jts.geom.Geometry;

public record PaisDtoSent(Long idPais,String nombre, String sigla, Long poblacion, Geometry geometry, String nombre_continente, Double areaKM2, Geometry centroide){
    public static PaisDtoSent convertirPaisDtoSent(Pais pais){
        return new PaisDtoSent(pais.getIdPais(),pais.getNombre(),pais.getSigla(),pais.getPoblacion(),pais.getGeometry(),pais.getNombreContinente(),pais.getAreaKM2(),pais.getCentroide());
    }
}