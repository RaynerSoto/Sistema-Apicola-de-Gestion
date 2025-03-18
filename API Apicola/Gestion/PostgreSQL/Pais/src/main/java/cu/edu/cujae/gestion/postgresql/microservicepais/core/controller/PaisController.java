package cu.edu.cujae.gestion.postgresql.microservicepais.core.controller;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.conexion.standard.EstadoConexion;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.apiGeografica.APIGeoPaisDto;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.pais.enviar.PaisEnviadoDto;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.exception.APIException;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.exception.AsignacionException;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.exception.SearchException;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.model.Pais;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.conexion.openfeign.EstadoOpenFeing;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.repository.PaisRepository;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.service.ContinentService;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.service.PaisService;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.conexion.apiExterna.APIGeograficaPais;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.conexion.apiExterna.APITraduction;
import io.micrometer.core.instrument.config.validate.ValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/gestion/1/pais")
@Tag(name = "Controllador de País",description = "Todos los endpoints que se ocupan del trabajo con la entidad País")
public class PaisController {
    private final PaisService paisService;
    private final ContinentService continentService;
    private final PaisRepository paisRepository;
    private final EstadoOpenFeing estadoOpenFeing;

    @Autowired
    public PaisController(PaisService paisService, ContinentService continentService, PaisRepository paisRepository, EstadoOpenFeing estadoOpenFeing) {
        this.paisService = paisService;
        this.continentService = continentService;
        this.paisRepository = paisRepository;
        this.estadoOpenFeing = estadoOpenFeing;
    }

    @GetMapping("/")
    @Operation(summary = "Listado de países",description = "Permite listar los países almacenados")
    public ResponseEntity<RespuestaEstandar> listarPaises(){
        return ResponseEntity.status(HttpStatus.OK).body(new RespuestaEstandar(paisService.buscarPaises()));
    }

    @PostMapping("/{nombre}")
    @Operation(summary = "Insertar país",
            description = "Método que permite insertar un país en el sistema, mediante la conexión a una API externa")
    public ResponseEntity<RespuestaEstandar> insertarPais(@PathVariable @NotBlank(message = "El nombre a buscar no puede estar nulo o vacío") String nombre) throws ValidationException, APIException, IOException, InterruptedException, SearchException, AsignacionException {
        String nombre_español = APITraduction.traduccionENES(nombre);
        APIGeoPaisDto apiGeoPaisDto = APIGeograficaPais.obtenerDatosPaises(nombre_español);
        Pais pais = new Pais(apiGeoPaisDto);
        paisService.insertarPais(pais);
        RespuestaEstandar respuestaEstandar = EstadoConexion.insertarEstado(PaisEnviadoDto.convertirPaisDtoSent(paisService.buscarPaisPorNombre(pais.getNombre())));
        return ResponseEntity.ok(new RespuestaEstandar("País: "+ nombre +" insertado. " + respuestaEstandar.getRespuesta().toString()));
    }

    @PutMapping("/{nombre}")
    @Operation(summary = "Actualizar país",
            description = "Método que permite actualizar un país en el sistema, mediante la conexión a una API externa")
    @Deprecated
    public ResponseEntity<RespuestaEstandar> actualizarPais(@PathVariable String nombre) throws Exception {
        String nombre_español = APITraduction.traduccionENES(nombre);
        APIGeoPaisDto apiGeoPaisDto = APIGeograficaPais.obtenerDatosPaises(nombre_español);
        paisService.actualizarPais(new Pais(apiGeoPaisDto));
        return ResponseEntity.ok(new RespuestaEstandar(String.format("País %s con datos actualizados", nombre)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Existe el país o no",
    description = "Conocer si un país por su id existe o no")
    @Deprecated
    public ResponseEntity<RespuestaEstandar> obtenerPaisSioNo(@PathVariable Long id){
        return ResponseEntity.ok(new RespuestaEstandar(paisRepository.existsById(id)));
    }
}
