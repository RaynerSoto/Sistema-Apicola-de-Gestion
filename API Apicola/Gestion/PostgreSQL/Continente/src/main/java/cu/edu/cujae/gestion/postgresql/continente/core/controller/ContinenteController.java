package cu.edu.cujae.gestion.postgresql.continente.core.controller;

import cu.edu.cujae.gestion.postgresql.continente.core.dto.response.RespuestaContinenteDtoList;
import cu.edu.cujae.gestion.postgresql.continente.core.dto.response.RespuestaContinenteDto;
import cu.edu.cujae.gestion.postgresql.continente.core.service.ContinenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/gestion/1/continente")
public class ContinenteController {
    private ContinenteService continenteService;

    @Autowired
    public ContinenteController(ContinenteService continenteService) {
        this.continenteService = continenteService;
    }

    @GetMapping("/")
    public ResponseEntity<RespuestaContinenteDtoList> getAll() {
        try {
            return ResponseEntity.ok(new RespuestaContinenteDtoList(true,continenteService.getAllContinentes()));
        } catch (Exception e) {
            return ResponseEntity.ok(new RespuestaContinenteDtoList(false,"No se pudo acceder al listado de países. Compruebe con el servicio técnico"));
        }
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<RespuestaContinenteDto> obternerContinenteXNombre(@PathVariable(value = "nombre") String nombre) {
        try {
            return ResponseEntity.ok(new RespuestaContinenteDto(true,continenteService.isCorrectContinente(nombre)));
        } catch (Exception e) {
            return ResponseEntity.ok(new RespuestaContinenteDto(false,e.getMessage()));
        }
    }
}
