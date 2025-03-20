package cu.edu.cujae.gestion.postgresql.geografia.core.dto.recibir;

public record PaisEstadoDto(String adminCode, String paisCode){
    @Override
    public String toString() {
        return String.format("{adminCode: %s, paisCode: %s}", adminCode, paisCode);
    }
}
