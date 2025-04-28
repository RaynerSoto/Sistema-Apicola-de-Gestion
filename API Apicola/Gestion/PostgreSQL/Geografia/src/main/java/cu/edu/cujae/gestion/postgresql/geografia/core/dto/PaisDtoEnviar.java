package cu.edu.cujae.gestion.postgresql.geografia.core.dto;
import cu.edu.cujae.gestion.postgresql.geografia.core.model.Pais;

public record PaisDtoEnviar(String nombrePais, String nombreCompleto, String sigla, Long poblacion,
                            String continente, Integer estadoCantidad,Double areaKM2) {
    public static PaisDtoEnviar toPais(Pais pais){
        return new PaisDtoEnviar(pais.getNombre(),pais.getNombreCompleto(), pais.getSigla(), pais.getPoblacion(), pais.getContinente().getNombre(), pais.getEstados().size(), pais.getAreaKM2());
    }
}
