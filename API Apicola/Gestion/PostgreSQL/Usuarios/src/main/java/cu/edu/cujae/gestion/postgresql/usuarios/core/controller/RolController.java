package cu.edu.cujae.gestion.postgresql.usuarios.core.controller;

import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.rol.RolDto;
import cu.edu.cujae.gestion.postgresql.usuarios.core.repository.RolRepository;
import cu.edu.cujae.gestion.postgresql.usuarios.core.service.RolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/gestion/1/rol")
@Tag(name = "Controllador del Rol")
public class RolController {
    private RolService rolService;
    private RolRepository rolRepository;

    @Autowired
    public RolController(RolService rolService, RolRepository rolRepository) {
        this.rolService = rolService;
        this.rolRepository = rolRepository;
    }

    @GetMapping("/")
    @Operation(summary = "Listado de roles",description = "Permite listar los roles")
    public RespuestaEstandar listadoRoles() {
        return new RespuestaEstandar(rolRepository.findAll().stream().map(rol -> RolDto.toDto(rol)).toList());
    }

    @GetMapping("/nombre")
    @Operation(summary = "Listado de nombre de los sexos",description = "Permite listar los nombres los roles")
    public RespuestaEstandar listadoRolesNombre() {
        return new RespuestaEstandar(rolService.listadoRoles());
    }

}
