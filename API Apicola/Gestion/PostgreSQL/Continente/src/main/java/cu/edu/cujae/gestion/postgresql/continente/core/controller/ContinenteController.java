package cu.edu.cujae.gestion.postgresql.continente.core.controller;

import cu.edu.cujae.gestion.postgresql.continente.core.dto.ContinenteDto;
import cu.edu.cujae.gestion.postgresql.continente.core.service.ContinenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gestion/1/continente")
public class ContinenteController {
    private ContinenteService continenteService;

    @Autowired
    public ContinenteController(ContinenteService continenteService) {
        this.continenteService = continenteService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ContinenteDto>> getAll() {
        try {
            return ResponseEntity.ok(continenteService.getAllContinentes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(List.of());
        }
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<ContinenteDto> obternerContinenteXNombre(@PathVariable String nombre) {
        try {
            return ResponseEntity.ok(continenteService.isCorrectContinente(nombre));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }
}
