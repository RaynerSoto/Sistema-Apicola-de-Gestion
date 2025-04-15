package cu.edu.cujae.gestion.postgresql.usuarios.core.dto.usuario;

public record UsuarioDto(String identificacion,
                         String nombre,
                         String username,
                         String password,
                         String email,
                         String estado,
                         String pais,
                         String direccionParticular,
                         String telefono,
                         String sexo,
                         Boolean marcaApicola,
                         Boolean certificado,
                         String rol
) {}