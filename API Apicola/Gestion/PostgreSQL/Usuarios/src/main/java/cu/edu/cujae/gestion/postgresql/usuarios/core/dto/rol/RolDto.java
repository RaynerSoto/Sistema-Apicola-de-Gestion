package cu.edu.cujae.gestion.postgresql.usuarios.core.dto.rol;

import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Rol;

public record RolDto(Long id, String nombre, String descripcion) {
    public static RolDto toDto(Rol rol) {
        return new RolDto(rol.getId_rol(), rol.getNombre(), rol.getDescripcion());
    }
}
