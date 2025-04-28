package cu.edu.cujae.gestion.postgresql.usuarios.core.dto.sexo;

import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Sexo;

public record SexoDto(Long id, String nombre) {
    public static SexoDto toDto(Sexo sexo) {
        return new SexoDto(sexo.getId_sexo(), sexo.getNombre());
    }
}
