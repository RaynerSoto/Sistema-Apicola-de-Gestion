package cu.edu.cujae.gestion.postgresql.microservicepais.core.controller;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.apiGeografica.APIGeoPaisDto;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.model.Pais;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.repository.PaisRepository;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.service.ContinentService;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.service.PaisService;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.utils.APIGeograficaPais;
import io.micrometer.core.instrument.config.validate.ValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/gestion/1/pais")
@Tag(name = "Controllador de País",description = "Todos los endpoints que se ocupan del trabajo con la entidad País")
public class PaisController {
    private final PaisService paisService;
    private final ContinentService continentService;

    private final PaisRepository paisRepository;

    @Autowired
    public PaisController(PaisService paisService, ContinentService continentService, PaisRepository paisRepository) {
        this.paisService = paisService;
        this.continentService = continentService;
        this.paisRepository = paisRepository;
    }

    @GetMapping("/")
    @Operation(summary = "Listado de países",description = "Permite listar los países almacenados")
    /**
     * Permite listar los países almacenados
     */
    public ResponseEntity<RespuestaEstandar> listarPaises(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(new RespuestaEstandar(paisService.buscarPaises()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RespuestaEstandar("No se puede obtener el listado de países. Contacte con su servicio de atención"));
        }
    }


    //Haciendo cambios para el insertar País a través de una API
    @PostMapping("/{nombre}")
    @Operation(summary = "Insertar país",
            description = "Método que permite insertar un país en el sistema, mediante la conexión a una API externa")
    public ResponseEntity<RespuestaEstandar> insertarPais(@PathVariable String nombre) throws ValidationException {
        try{
            APIGeoPaisDto apiGeoPaisDto = APIGeograficaPais.obtenerDatosPaises(nombre);
            paisService.insertarPais(new Pais(apiGeoPaisDto));
            return ResponseEntity.ok(new RespuestaEstandar("País: "+ nombre +" insertado"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new RespuestaEstandar(e.getMessage()));
        }
    }

    @PutMapping("/{nombre}")
    @Operation(summary = "Actualizar país",
            description = "Método que permite actualizar un país en el sistema, mediante la conexión a una API externa")
    public ResponseEntity<RespuestaEstandar> actualizarPais(@PathVariable String nombre){
        try{
            APIGeoPaisDto apiGeoPaisDto = APIGeograficaPais.obtenerDatosPaises(nombre);
            paisService.actualizarPais(new Pais(apiGeoPaisDto));
            return ResponseEntity.ok(new RespuestaEstandar(String.format("País %s con datos actualizados", nombre)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new RespuestaEstandar(e.getMessage()));
        }
    }
}
