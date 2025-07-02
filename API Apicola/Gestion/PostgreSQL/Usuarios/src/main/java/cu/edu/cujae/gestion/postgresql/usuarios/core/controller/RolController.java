package cu.edu.cujae.gestion.postgresql.usuarios.core.controller;

import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.SearchException;
import cu.edu.cujae.gestion.postgresql.usuarios.core.serviceController.RolControllerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para manejar las operaciones relacionadas con los roles.
 * Proporciona endpoints para listar roles y sus nombres.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0.0
 * @see cu.edu.cujae.gestion.postgresql.usuarios.core.serviceController.RolControllerService
 */
@RestController
@RequestMapping("/api/v1/gestion/1/rol")
@Tag(name = "Controllador del Rol")
public class RolController {
    private final RolControllerService rolControllerService;

    @Autowired
    public RolController(RolControllerService rolControllerService) {
        this.rolControllerService = rolControllerService;
    }

    /**
     * Endpoint para listar todos los roles.
     * @return ResponseEntity con la lista de roles.
     * @throws SearchException si ocurre un error al buscar los roles.
     * @autor Rayner Alejandro Soto Martínez
     * @see cu.edu.cujae.gestion.postgresql.usuarios.core.serviceController.RolControllerService
     * @version 1.0.0
     */
    @GetMapping("/")
    @Operation(summary = "Listado de roles",description = "Permite listar los roles")
    public ResponseEntity<RespuestaEstandar> listadoRoles() throws SearchException {
       return rolControllerService.listadoRoles();
    }

    /**
     * Endpoint para listar los nombres de los roles.
     * @autor Rayner Alejandro Soto Martínez
     * @return ResponseEntity con la lista de nombres de roles.
     * @throws SearchException si ocurre un error al buscar los nombres de los roles.
     * @see cu.edu.cujae.gestion.postgresql.usuarios.core.serviceController.RolControllerService
     * @version 1.0.0
     */
    @GetMapping("/nombre")
    @Operation(summary = "Listado de nombre de los sexos",description = "Permite listar los nombres los roles")
    public ResponseEntity<RespuestaEstandar> listadoRolesNombre() throws SearchException {
        return rolControllerService.listadoRolesNombre();
    }

    @GetMapping("/{nombre}")
    @Operation(summary = "Buscar un rol por su nombre", description = "")
    public ResponseEntity<RespuestaEstandar>obtenerRol(@PathVariable @Valid
                                                           @Size(min = 4,max = 100,message = "El nombre del rol se encuentra entre 4 y 100 caracteres")
                                                           @NotBlank(message = "El nombre del rol no puede ser nulo o estar vacío") String nombre) throws SearchException {
        return rolControllerService.obtenerRolNombre(nombre);
    }

}
