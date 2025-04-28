package cu.edu.cujae.gestion.postgresql.geografia.core.controller;

import cu.edu.cujae.gestion.postgresql.geografia.core.dto.PaisDtoEnviar;
import cu.edu.cujae.gestion.postgresql.geografia.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.geografia.core.repository.PaisRepository;
import cu.edu.cujae.gestion.postgresql.geografia.core.service.PaisService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/gestion/1/pais")
@Tag(name = "Controlador del país")
public class PaisController {

    private final PaisRepository paisRepository;
    private final PaisService paisService;

    @Autowired
    public PaisController(PaisRepository paisRepository, PaisService paisService) {
        this.paisRepository = paisRepository;
        this.paisService = paisService;
    }

    @SneakyThrows
    @GetMapping("/")
    public ResponseEntity<RespuestaEstandar> obtenerPaises() {
        return ResponseEntity.ok(new RespuestaEstandar(paisRepository.findAll().stream().map(PaisDtoEnviar::toPais)));
    }
}
