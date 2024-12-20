package cu.edu.cujae.gestion.postgresql.microservicepais.core.controller;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.recibir.PaisDtoReci;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.respuesta.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.model.Pais;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.service.ContinentService;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.service.PaisService;
import io.micrometer.core.instrument.config.validate.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/gestion/1/pais")
public class PaisController {
    private final PaisService paisService;
    private final ContinentService continentService;

    @Autowired
    public PaisController(PaisService paisService, ContinentService continentService) {
        this.paisService = paisService;
        this.continentService = continentService;
    }

    @GetMapping("/")
    public ResponseEntity<RespuestaEstandar> listadoPaises(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(new RespuestaEstandar(paisService.buscarPaises()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RespuestaEstandar("No se puede obtener el listado de países. Contacte con su servicio de atención"));
        }
    }

    @PostMapping("/")
    public ResponseEntity<RespuestaEstandar> insertarPais(@RequestBody PaisDtoReci paisDtoReci) throws ValidationException {
        try{
            String continente = continentService.obtenerContinente(paisDtoReci.nombre_continente());
            Pais pais = paisDtoReci.nombre_continente().equals(continente) ? new Pais(paisDtoReci):new Pais(paisDtoReci,continente);
            paisService.insertarPais(pais);
            return ResponseEntity.ok(new RespuestaEstandar("País: "+paisDtoReci.nombre()+" insertado"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new RespuestaEstandar(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaEstandar> modificarPais(@RequestBody PaisDtoReci paisDtoReci, @PathVariable Long id){
        try {
            String continente = continentService.obtenerContinente(paisDtoReci.nombre_continente());
            if (paisDtoReci.nombre_continente().equals(String.valueOf(continente)))
                paisService.insertarPais(new Pais(paisDtoReci,id));
            else
                paisService.insertarPais(new Pais(paisDtoReci, continente,id));
            return ResponseEntity.ok(new RespuestaEstandar("País modificado con éxito"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new RespuestaEstandar(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaEstandar> eliminarPais(@PathVariable Long id) {
        try {
            paisService.eliminarPais(id);
            return ResponseEntity.ok(new RespuestaEstandar("País eliminado con éxito"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new RespuestaEstandar(e.getMessage()));
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<RespuestaEstandar> buscarPaisID(@PathVariable Long id){
        try {
            return ResponseEntity.ok(new RespuestaEstandar(paisService.buscarPais(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new RespuestaEstandar(e.getMessage()));
        }
    }
}
