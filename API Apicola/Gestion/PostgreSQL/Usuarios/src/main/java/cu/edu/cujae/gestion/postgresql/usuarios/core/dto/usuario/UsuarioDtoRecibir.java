package cu.edu.cujae.gestion.postgresql.usuarios.core.dto.usuario;

import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Usuario;

public record UsuarioDtoRecibir(String identificacion,
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
) {
    public UsuarioDtoRecibir(Usuario usuario,String estado,String pais){
        return new UsuarioDtoRecibir();
    }
}