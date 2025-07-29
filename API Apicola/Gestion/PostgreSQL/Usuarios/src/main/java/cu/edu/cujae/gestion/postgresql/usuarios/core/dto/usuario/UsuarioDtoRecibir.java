package cu.edu.cujae.gestion.postgresql.usuarios.core.dto.usuario;

import jakarta.validation.constraints.*;

public record UsuarioDtoRecibir(
        @NotBlank(message = "La identificación del usuario no puede estar vacía o ser nula")
        @Size(max = 50)
        String identificacion,

        @NotBlank(message = "El nombre completo del usuario no puede estar vacío o ser nulo")
        @Size(max = 255)
        String nombre,

        @NotBlank(message = "El nombre de usuario no puede estar vacío o ser nulo")
        @Size(max = 255)
        String username,

        @NotBlank(message = "La contraseña del usuario no puede estar vacía o ser nula")
        @Size(max = 255)
        String password,

        @Email(message = "El correo no tiene el formato específico")
        @NotBlank(message = "El correo no puede estar vacío")
        @Size(max = 100)
        String email,

        @NotBlank(message = "El estado no puede estar vacío o ser nulo")
        String estado,

        @NotBlank(message = "El país no puede estar vacío o ser nulo")
        String pais,

        @NotBlank(message = "La dirección particular no puede estar vacía o ser nula")
        @Size(max = 255)
        String direccionParticular,

        @NotBlank(message = "El teléfono no puede estar vacío o ser nulo")
        @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Número de teléfono inválido")
        String telefono,

        @NotBlank(message = "El sexo no puede estar vacío o ser nulo")
        String sexo,

        @NotNull(message = "El valor de si es una marca Apícola no puede ser null")
        Boolean marcaApicola,

        @NotNull(message = "El valor de si es un apicultor certificado no puede ser null")
        Boolean certificado,

        @NotBlank(message = "El rol no puede estar vacío o ser nulo")
        String rol
) {
}