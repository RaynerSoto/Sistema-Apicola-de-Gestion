package cu.edu.cujae.gestion.postgresql.microservicepais.core.controller;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.recibir.PaisDtoReci;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.response.RespuestaPaisDto;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.response.RespuestaPaisDtoList;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.mapping.Pais;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.openfeign.ContinenteOpenFeign;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.service.ContinentService;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.service.PaisService;
import io.micrometer.core.instrument.config.validate.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/gestion/1/pais")
public class PaisController {
    private final PaisService paisService;
    private final ContinenteOpenFeign continenteOpenFeign;
    private final ContinentService continentService;

    @Autowired
    public PaisController(PaisService paisService, ContinenteOpenFeign continenteOpenFeign, ContinentService continentService) {
        this.paisService = paisService;
        this.continenteOpenFeign = continenteOpenFeign;
        this.continentService = continentService;
    }

    @GetMapping("/")
    public ResponseEntity<RespuestaPaisDtoList> listadoPaises(){
        try{
            return ResponseEntity.ok(new RespuestaPaisDtoList(true,paisService.buscarPaises()));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(new RespuestaPaisDtoList(false,"No se puede obtener el listado de países. Contacte con su servicio de atención"));
        }
    }

    @PostMapping("/")
    public ResponseEntity<RespuestaPaisDto> insertarPais(@RequestBody PaisDtoReci paisDtoReci) throws ValidationException {
        try{
            String continente = continentService.obtenerContinente(paisDtoReci.nombre_continente());
            if (paisDtoReci.nombre_continente().equals(String.valueOf(continente)))
                paisService.insertarPais(new Pais(paisDtoReci));
            else
                paisService.insertarPais(new Pais(paisDtoReci, continente));
            return ResponseEntity.ok(new RespuestaPaisDto(true,"País insertado"));
        }catch (Exception e){
            System.out.println(e.getCause().getMessage());
            return ResponseEntity.ok(new RespuestaPaisDto(false,e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaPaisDto> modificarPais(@RequestBody PaisDtoReci paisDtoReci, @PathVariable Long id){
        try {
            String continente = continentService.obtenerContinente(paisDtoReci.nombre_continente());
            if (paisDtoReci.nombre_continente().equals(String.valueOf(continente)))
                paisService.insertarPais(new Pais(paisDtoReci,id));
            else
                paisService.insertarPais(new Pais(paisDtoReci, continente,id));
            return ResponseEntity.ok(new RespuestaPaisDto(true,""));
        } catch (Exception e) {
            return ResponseEntity.ok(new RespuestaPaisDto(false,e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaPaisDto> eliminarPais(@PathVariable Long id) {
        try {
            paisService.eliminarPais(id);
            return ResponseEntity.ok(new RespuestaPaisDto(true, "País eliminado con éxito"));
        } catch (Exception e) {
            return ResponseEntity.ok(new RespuestaPaisDto(false,e.getMessage()));
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<RespuestaPaisDto> buscarPaisID(@PathVariable Long id){
        try {
            return ResponseEntity.ok(new RespuestaPaisDto(true,paisService.buscarPais(id)));
        } catch (Exception e) {
            return ResponseEntity.ok(new RespuestaPaisDto(false,e.getMessage()));
        }
    }
}
