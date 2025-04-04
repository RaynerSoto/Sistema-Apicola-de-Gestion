package cu.edu.cujae.gestion.postgresql.usuarios.core.controller;

import cu.edu.cujae.gestion.postgresql.usuarios.core.repository.RolRepositiry;
import cu.edu.cujae.gestion.postgresql.usuarios.core.service.RolService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/gestion/1/rol")
@Tag(name = "Controllador del Rol")
public class RolController {
    private RolService rolService;
    private RolRepositiry rolRepositiry;

    @Autowired
    public RolController(RolService rolService, RolRepositiry rolRepositiry) {
        this.rolService = rolService;
        this.rolRepositiry = rolRepositiry;
    }
}
