package cu.edu.cujae.gestion.postgresql.usuarios.core.dto.paisEstado;

public record PaisEstadoComprobar(String nombrePais, String nombreEstado) {
    public String toJSON() {
        return String.format("{Pais: %s, Estado: %s}", nombrePais, nombreEstado);
    }
}
