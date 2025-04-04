package cu.edu.cujae.gestion.postgresql.usuarios.core.controller;

import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.service.SexoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gestion/1/sexo")
public class SexoController {
    private SexoService sexoService;

    @Autowired
    public SexoController(SexoService sexoService) {
        this.sexoService = sexoService;
    }

    @GetMapping("/")
    @Operation(summary = "Listado de sexos",description = "Permite listar los nombres los sexos")
    public RespuestaEstandar listadoSexos() {
        return new RespuestaEstandar(sexoService.listadoSexos());
    }
}
