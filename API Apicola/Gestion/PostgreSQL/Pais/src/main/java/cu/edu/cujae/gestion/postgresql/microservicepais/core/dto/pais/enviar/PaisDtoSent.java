package cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.pais.enviar;

import cu.edu.cujae.gestion.postgresql.microservicepais.core.model.Pais;
import org.locationtech.jts.geom.Geometry;

import java.util.Optional;

public record PaisDtoSent(Long idPais,String nombre, String sigla, Long poblacion, Geometry geometry, String nombre_continente, Double areaKM2, Geometry centroide){

    public static PaisDtoSent convertirPaisDtoSent(Pais pais){
        return new PaisDtoSent(pais.getIdPais(),pais.getNombre(),pais.getSigla(),pais.getPoblacion(),pais.getGeometry(),pais.getNombreContinente(),pais.getAreaKM2(),pais.getCentroide());
    }

    public static PaisDtoSent convertirPaisDtoSent(Optional<Pais> pais){
        return new PaisDtoSent(pais.get().getIdPais(),pais.get().getNombre(),pais.get().getSigla(),pais.get().getPoblacion(),pais.get().getGeometry(),pais.get().getNombreContinente(),pais.get().getAreaKM2(),pais.get().getCentroide());
    }
}