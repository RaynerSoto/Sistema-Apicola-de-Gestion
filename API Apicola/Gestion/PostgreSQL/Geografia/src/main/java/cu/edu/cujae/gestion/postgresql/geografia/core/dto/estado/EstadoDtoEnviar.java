package cu.edu.cujae.gestion.postgresql.geografia.core.dto.estado;

import cu.edu.cujae.gestion.postgresql.geografia.core.model.Estado;

public record EstadoDtoEnviar(String nombre, Long poblacion, String pais, String codigo, Double areaKm2) {
    public static EstadoDtoEnviar toEstado(Estado estado) {
        return new EstadoDtoEnviar(estado.getNombre(), estado.getPoblacion(), estado.getPais().getNombre(), estado.getCodigo(), estado.getAreaKm2());
    }
}
