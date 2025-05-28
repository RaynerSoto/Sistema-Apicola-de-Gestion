package cu.edu.cujae.gestion.postgresql.geografia.core.controller;

import cu.edu.cujae.gestion.postgresql.geografia.core.conexion.apiExterna.APIGeograficaEstado;
import cu.edu.cujae.gestion.postgresql.geografia.core.conexion.apiExterna.APIGeograficaPais;
import cu.edu.cujae.gestion.postgresql.geografia.core.conexion.apiExterna.APITraduction;
import cu.edu.cujae.gestion.postgresql.geografia.core.dto.apiGeografica.APIGeoEstadoDto;
import cu.edu.cujae.gestion.postgresql.geografia.core.dto.apiGeografica.APIGeoPaisDto;
import cu.edu.cujae.gestion.postgresql.geografia.core.dto.paisEstado.PaisEstadoDtoComprobar;
import cu.edu.cujae.gestion.postgresql.geografia.core.dto.paisEstado.PaisEstadoDtoID;
import cu.edu.cujae.gestion.postgresql.geografia.core.dto.paisEstado.PaisEstadoDtoNombre;
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

/**
 * Controlador REST para gestionar las operaciones relacionadas con la geografía.
 * Este controlador proporciona endpoints para insertar datos geográficos de países y estados,
 * así como para comprobar la relación entre países y estados.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
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

    /**
     * Método que permite insertar un país en el sistema, mediante la conexión a una API externa.
     * @param nombre El nombre del país que se desea insertar en el sistema.
     * @return ResponseEntity con un mensaje de éxito que indica que el país ha sido insertado correctamente,
     * @throws APIException si ocurre un error al conectar con la API externa o al procesar los datos.
     * @throws IOException si ocurre un error de entrada/salida durante la conexión a la API.
     * @throws InterruptedException si la operación es interrumpida durante la solicitud HTTP.
     * @throws SearchException si ocurre un error al buscar datos relacionados con el país o estado.
     * @throws AsignacionException si ocurre un error al asignar datos a los objetos del modelo.
     * @throws OperationsException si ocurre un error al realizar operaciones relacionadas con la inserción de datos.
     */
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

    /**
     * Método que permite comprobar si un país y un estado tienen relación dentro del sistema.
     * Este método verifica si el país existe en la base de datos y si el estado pertenece a ese país.
     * @param paisEstado Un objeto que contiene el nombre del país y el nombre del estado a comprobar.
     *                   El objeto debe contener los campos `nombrePais` y `nombreEstado`.
     *                   Si alguno de estos campos es nulo o vacío, se lanzará una excepción.
     * @return ResponseEntity con un objeto RespuestaEstandar que indica si la relación existe o no.
     * @throws SearchException si no se encuentra el país o si el estado no pertenece al país especificado.
     * @deprecated Este método está obsoleto y se recomienda utilizar el método `obtenerPaisEstadoID` en su lugar.
     */
    @PostMapping("/PaisEstado/Deprecated")
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

    /**
     * Método que permite comprobar si un país y un estado tienen relación dentro del sistema
     * y retornar sus identificadores.
     * Este método verifica si el país existe en la base de datos y si el estado pertenece a ese país,
     * y devuelve los identificadores del país y del estado si la relación es válida.
     * @param paisEstado Un objeto que contiene el nombre del país y el nombre del estado a comprobar.
     *                   El objeto debe contener los campos `nombrePais` y `nombreEstado`.
     *                   Si alguno de estos campos es nulo o vacío, se lanzará una excepción.
     * @return ResponseEntity con un objeto RespuestaEstandar que contiene los identificadores del país y del estado.
     * @throws SearchException si no se encuentra el país o si el estado no pertenece al país especificado.
     */
    @PostMapping("/PaisEstado")
    @Operation(summary = "Comprobación de país-estado y retorno de los identificadores",
            description = "Permite comprobar de los países que puede acceder al sistema, el país y estado tienen relación")
    public ResponseEntity<RespuestaEstandar> obtenerPaisEstadoID(@RequestBody PaisEstadoDtoComprobar paisEstado) throws SearchException {
        if(!paisRepository.existsPaisByNombre(paisEstado.nombrePais()))
            throw new SearchException("No ha podido encontrar el país");
        else{
            Optional<Pais> pais = paisRepository.findByNombreEquals(paisEstado.nombrePais());
            Optional<Estado> estado = pais.get().getEstados().stream().filter(s-> s.getNombre().equalsIgnoreCase(paisEstado.nombreEstado())).findFirst();
            if(pais.isPresent() && estado.isPresent()){
                return ResponseEntity.ok(new RespuestaEstandar(new PaisEstadoDtoID(pais.get().getIdPais(), estado.get().getIdEstado())));
            }else{
                throw new SearchException(String.format("Estado: %s no encontrado dentro del país: %s",paisEstado.nombreEstado(),paisEstado.nombrePais()));
            }
        }
    }

    /**
     * Método que permite comprobar si un país y un estado tienen relación dentro del sistema
     * y retornar sus nombres.
     * @param idPais El identificador del país a comprobar.
     *               Debe ser un valor numérico positivo.
     *               Si el identificador es nulo o negativo, se lanzará una excepción.
     * @param idEstado El identificador del estado a comprobar.
     *                 Debe ser un valor numérico positivo.
     *                 Si el identificador es nulo o negativo, se lanzará una excepción.
     * @return ResponseEntity con un objeto RespuestaEstandar que contiene los nombres del país y del estado.
     * @throws SearchException si no se encuentra el país o si el estado no pertenece al país especificado.
     */
    @GetMapping("/PaisEstado/{idPais}/{idEstado}")
    @Operation(summary = "Comprobación de país-estado y retorno de los nombres",
            description = "Permite comprobar de los países que puede acceder al sistema, el país y estado tienen relación")
    public ResponseEntity<RespuestaEstandar> obtenerPaisEstadoNombre(@PathVariable Long idPais, @PathVariable Long idEstado) throws SearchException {
        if (!paisRepository.existsPaisByIdPais(idPais))
            throw new SearchException("No ha podido encontrar el país");
        else {
            Optional<Pais> pais = paisRepository.findByIdPaisEquals(idPais);
            if(!pais.get().getEstados().stream().anyMatch(estado -> estado.getIdEstado().equals(idEstado))){
                throw new SearchException("No ha podido encontrar el estado");
            }
            else
                return ResponseEntity.ok(new RespuestaEstandar(new PaisEstadoDtoNombre(pais.get().getNombre(), pais.get().getEstados().stream().filter(estado -> estado.getIdEstado().equals(idEstado)).findFirst().get().getNombre())));
        }
    }
}