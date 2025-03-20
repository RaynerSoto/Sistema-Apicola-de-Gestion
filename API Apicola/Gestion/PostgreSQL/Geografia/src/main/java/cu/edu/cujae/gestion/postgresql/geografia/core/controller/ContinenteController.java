package cu.edu.cujae.gestion.postgresql.geografia.core.controller;
import cu.edu.cujae.gestion.postgresql.continente.core.dto.respuesta.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.continente.core.service.ContinenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<RespuestaEstandar> obtenerContinentes() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new RespuestaEstandar(continenteService.getAllContinentes()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RespuestaEstandar("No se pudo acceder al listado de países. Compruebe con el servicio técnico"));
        }
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<RespuestaEstandar> obternerContinenteXNombre(@PathVariable(value = "nombre") String nombre) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new RespuestaEstandar(continenteService.isCorrectContinente(nombre)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RespuestaEstandar(e.getMessage()));
        }
    }
}
