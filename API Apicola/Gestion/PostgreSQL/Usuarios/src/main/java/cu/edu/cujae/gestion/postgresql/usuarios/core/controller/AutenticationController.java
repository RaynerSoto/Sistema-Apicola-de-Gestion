package cu.edu.cujae.gestion.postgresql.usuarios.core.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Tag(name = "Controlador de autenticación")
public class AutenticationController {
}
