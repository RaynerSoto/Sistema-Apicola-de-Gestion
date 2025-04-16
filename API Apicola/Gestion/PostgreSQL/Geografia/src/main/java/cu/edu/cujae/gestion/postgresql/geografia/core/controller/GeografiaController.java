package cu.edu.cujae.gestion.postgresql.geografia.core.controller;

import cu.edu.cujae.gestion.postgresql.geografia.core.conexion.apiExterna.APIGeograficaEstado;
import cu.edu.cujae.gestion.postgresql.geografia.core.conexion.apiExterna.APIGeograficaPais;
import cu.edu.cujae.gestion.postgresql.geografia.core.conexion.apiExterna.APITraduction;
import cu.edu.cujae.gestion.postgresql.geografia.core.dto.apiGeografica.APIGeoEstadoDto;
import cu.edu.cujae.gestion.postgresql.geografia.core.dto.apiGeografica.APIGeoPaisDto;
import cu.edu.cujae.gestion.postgresql.geografia.core.dto.paisEstado.PaisEstadoDtoComprobar;
import cu.edu.cujae.gestion.postgresql.geografia.core.dto.paisEstado.PaisEstadoDtoID;
import cu.edu.cujae.gestion.postgresql.geografia.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.geografia.core.dto.respuestaGenerica.RespuestaEstandar2doNivel;
import cu.edu.cujae.gestion.postgresql.geografia.core.exception.APIException;
import cu.edu.cujae.gestion.postgresql.geografia.core.exception.AsignacionException;
import cu.edu.cujae.gestion.postgresql.geografia.core.exception.SearchException;
import cu.edu.cujae.gestion.postgresql.geografia.core.model.Estado;
import cu.edu.cujae.gestion.postgresql.geografia.core.model.Pais;
import cu.edu.cujae.gestion.postgresql.geografia.core.repository.EstadoRepository;
import cu.edu.cujae.gestion.postgresql.geografia.core.repository.PaisRepository;
import cu.edu.cujae.gestion.postgresql.geografia.core.service.ContinenteService;
import cu.edu.cujae.gestion.postgresql.geografia.core.service.EstadoService;
import cu.edu.cujae.gestion.postgresql.geografia.core.service.MunicipioService;
import cu.edu.cujae.gestion.postgresql.geografia.core.service.PaisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.OperationsException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/gestion/1/geografia")
@Tag(name = "Controlador de la Geografía")
public class GeografiaController {
    private PaisService paisService;
    private ContinenteService continenteService;
    private MunicipioService municipioService;
    private EstadoService estadoService;
    private EstadoRepository estadoRepository;
    private PaisRepository paisRepository;

    @Autowired
    public GeografiaController(PaisService paisService, ContinenteService continenteService, MunicipioService municipioService, EstadoService estadoService, EstadoRepository estadoRepository, PaisRepository paisRepository) {
        this.paisService = paisService;
        this.continenteService = continenteService;
        this.municipioService = municipioService;
        this.estadoService = estadoService;
        this.estadoRepository = estadoRepository;
        this.paisRepository = paisRepository;
    }

    @PostMapping("/{nombre}")
    @Operation(summary = "Insertar país",
            description = "Método que permite insertar un país en el sistema, mediante la conexión a una API externa")
    public ResponseEntity<RespuestaEstandar> insertarDatosGeograficos(@PathVariable @NotBlank(message = "El nombre a buscar no puede estar nulo o vacío") String nombre) throws APIException, IOException, InterruptedException, SearchException, AsignacionException, OperationsException {
        String nombre_español = APITraduction.traduccionENES(nombre);
        APIGeoPaisDto apiGeoPaisDto = APIGeograficaPais.obtenerDatosPaises(nombre_español);
        Pais pais = new Pais(apiGeoPaisDto, continenteService.buscarContinente(APITraduction.traduccionENES(apiGeoPaisDto.region())));
        APIGeoEstadoDto apiGeoEstadoDto = APIGeograficaEstado.obtenerDatosEstado(pais.getSigla());
        List<Estado> estados = apiGeoEstadoDto.geonames().stream()
                .map(e -> new Estado(e,pais))
                .filter(Objects::nonNull)
                .toList();
        pais.setEstados(estados);
        if (pais.getEstados().size() == apiGeoEstadoDto.totalResultsCount()){
            paisService.insertarPais(pais);
            return ResponseEntity.ok(new RespuestaEstandar(String.format("Insertado el país %s con un total de %d estados/provincias",pais.getNombre(),pais.getEstados().size())));
        }
        else {
            throw new OperationsException("No se ha podioo completar la operación debido a que no se han podido cargar todos los datos");
        }
    }

    @PostMapping("/PaisEstado")
    @Operation(summary = "Comprobación de país-estado",
            description = "Permite comprobar de los países que puede acceder al sistema, el país y estado tienen relación")
    @Deprecated
    public ResponseEntity<RespuestaEstandar> comprobarPaisEstado(@RequestBody PaisEstadoDtoComprobar paisEstado){
        try {
            if(!paisRepository.existsPaisByNombre(paisEstado.nombrePais()))
                throw new SearchException("No ha podido encontrar el país");
            else{
                Pais pais = paisRepository.findByNombreEquals(paisEstado.nombrePais()).get();
                Boolean encontrado = pais.getEstados().stream().anyMatch(estado -> estado.getNombre().equals(paisEstado.nombreEstado()));
                if(encontrado){
                    return ResponseEntity.ok(new RespuestaEstandar(true));
                }
                else
                    throw new SearchException(String.format("Estado: %s no encontrado dentro del país: %s",paisEstado.nombreEstado(),paisEstado.nombrePais()));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(new RespuestaEstandar2doNivel(true,e.getMessage()));
        }
    }

    @PostMapping("/PaisEstado")
    @Operation(summary = "Comprobación de país-estado y retorno de los identificadores",
            description = "Permite comprobar de los países que puede acceder al sistema, el país y estado tienen relación")
    public ResponseEntity<RespuestaEstandar> obtenerPaisEstado(@RequestBody PaisEstadoDtoComprobar paisEstado) throws SearchException {
        if(!paisRepository.existsPaisByNombre(paisEstado.nombrePais()))
            throw new SearchException("No ha podido encontrar el país");
        else{
            Optional<Pais> pais = paisRepository.findByNombreEquals(paisEstado.nombrePais());
            Optional<Estado> estado = pais.get().getEstados().stream().filter(s-> s.getNombre().equals(paisEstado.nombreEstado())).findFirst();
            if(pais.isPresent() && estado.isPresent()){
                return ResponseEntity.ok(new RespuestaEstandar(new PaisEstadoDtoID(pais.get().getIdPais(), estado.get().getIdEstado())));
            }else{
                throw new SearchException(String.format("Estado: %s no encontrado dentro del país: %s",paisEstado.nombreEstado(),paisEstado.nombrePais()));
            }
        }
    }
}