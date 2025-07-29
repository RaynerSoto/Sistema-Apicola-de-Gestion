package cu.edu.cujae.gestion.postgresql.usuarios.core.dto.usuario;

import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Usuario;

public record UsuarioDtoEnviar (
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

    public static UsuarioDtoEnviar obtenerUsuarioDtoEnviar(Usuario usuario, String estado, String pais) {
        return new UsuarioDtoEnviar(
                usuario.getIdentificacion(),
                usuario.getNombre(),
                usuario.getUsername(),
                usuario.getRol().getNombre(),
                usuario.getEmail(),
                estado,
                pais,
                usuario.getDireccionParticular(),
                usuario.getTelefono(),
                usuario.getSexo().getNombre(),
                usuario.getMarcaApicola(),
                usuario.getCertificado());
    }

    public static UsuarioDtoEnviar obtenerUsuarioDtoEnviarNull() {
        return new UsuarioDtoEnviar(null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static UsuarioDtoEnviar obtenerdeUsuario(Usuario usuario, String estado, String pais) {
        return new UsuarioDtoEnviar(usuario.getIdentificacion(),
                usuario.getNombre(),
                usuario.getUsername(),
                usuario.getRol().getNombre(),
                usuario.getEmail(),
                estado,
                pais,
                usuario.getDireccionParticular(),
                usuario.getTelefono(),
                usuario.getSexo().getNombre(),
                usuario.getMarcaApicola(),
                usuario.getCertificado());
    }
}
