package cu.edu.cujae.gestion.postgresql.usuarios.core.controller;

import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.sexo.SexoDto;
import cu.edu.cujae.gestion.postgresql.usuarios.core.repository.SexoRepository;
import cu.edu.cujae.gestion.postgresql.usuarios.core.service.SexoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gestion/1/sexo")
@Tag(name = "Controlador de sexo")
public class SexoController {
    private SexoService sexoService;
    private SexoRepository sexoRepository;

    @Autowired
    public SexoController(SexoService sexoService,SexoRepository sexoRepository) {
        this.sexoService = sexoService;
        this.sexoRepository = sexoRepository;
    }

    @GetMapping("/")
    @Operation(summary = "Listado de sexos",description = "Permite listar los sexos")
    public RespuestaEstandar listadoSexos() {
        return new RespuestaEstandar(sexoRepository.findAll().stream().map(sexo -> SexoDto.toDto(sexo)).toList());
    }

    @GetMapping("/nombre")
    @Operation(summary = "Listado de nombre de los sexos",description = "Permite listar los nombres los sexos")
    public RespuestaEstandar listadoSexosNombre() {
        return new RespuestaEstandar(sexoService.listadoSexos());
    }
}
