package cu.edu.cujae.gestion.postgresql.usuarios.core.dto.usuario;

import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Usuario;

public record UsuarioDtoEnviar (
        Long idUsuario,
        String identificacion,
        String nombre,
        String username,
        String rol,
        String email,
        String estado,
        String pais,
        String direccionParticular,
        String telefono,
        String sexo,
        Boolean marcaApicola,
        Boolean certificado

){
    public static UsuarioDtoEnviar obtenerUsuarioDtoEnviar(Usuario usuario) {

    }
}
