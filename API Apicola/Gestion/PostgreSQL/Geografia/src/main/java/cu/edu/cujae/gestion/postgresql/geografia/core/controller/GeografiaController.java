package cu.edu.cujae.gestion.postgresql.geografia.core.controller;

import cu.edu.cujae.gestion.postgresql.geografia.core.dto.paisEstado.PaisEstadoDtoNombre;
import cu.edu.cujae.gestion.postgresql.geografia.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.geografia.core.exception.APIException;
import cu.edu.cujae.gestion.postgresql.geografia.core.exception.AsignacionException;
import cu.edu.cujae.gestion.postgresql.geografia.core.exception.SearchException;
import cu.edu.cujae.gestion.postgresql.geografia.core.serviceController.GeografiaControllerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.OperationsException;
import java.io.IOException;

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
    private final GeografiaControllerService geografiaControllerService;

    @Autowired
    public GeografiaController(GeografiaControllerService geografiaControllerService) {
        this.geografiaControllerService = geografiaControllerService;
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
    public ResponseEntity<RespuestaEstandar> insertarDatosGeograficos(@PathVariable @Valid
                                                                          @Size(min = 4,max = 100,message = "El nombre del país se encuentra entre 4 y 100 caracteres")
                                                                          @NotBlank(message = "El nombre a buscar no puede estar nulo o vacío") String nombre) throws Exception {
        return geografiaControllerService.insertarDatosGeograficos(nombre);
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
    public ResponseEntity<RespuestaEstandar> comprobarPaisEstado(@RequestBody @Valid PaisEstadoDtoNombre paisEstado){
        return geografiaControllerService.comprobarPaisEstado(paisEstado);
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
    public ResponseEntity<RespuestaEstandar> obtenerPaisEstadoID(@RequestBody PaisEstadoDtoNombre paisEstado) throws Exception {
        return geografiaControllerService.obtenerPaisEstadoID(paisEstado);
    }
//
//    /**
//     * Método que permite comprobar si un país y un estado tienen relación dentro del sistema
//     * y retornar sus nombres.
//     * @param idPais El identificador del país a comprobar.
//     *               Debe ser un valor numérico positivo.
//     *               Si el identificador es nulo o negativo, se lanzará una excepción.
//     * @param idEstado El identificador del estado a comprobar.
//     *                 Debe ser un valor numérico positivo.
//     *                 Si el identificador es nulo o negativo, se lanzará una excepción.
//     * @return ResponseEntity con un objeto RespuestaEstandar que contiene los nombres del país y del estado.
//     * @throws SearchException si no se encuentra el país o si el estado no pertenece al país especificado.
//     */
    @GetMapping("/PaisEstado/{idPais}/{idEstado}")
    @Operation(summary = "Comprobación de país-estado y retorno de los nombres",
            description = "Permite comprobar de los países que puede acceder al sistema, el país y estado tienen relación")
    public ResponseEntity<RespuestaEstandar> obtenerPaisEstadoNombre(@PathVariable @Valid @Min(value = 1,message = "El valor mínimo del id del Paísdebe ser 1") Long idPais
            , @PathVariable @Valid @Min(value = 1,message = "El valor mínimo del id del Estado ser 1")Long idEstado) throws Exception {
        return geografiaControllerService.obtenerPaisEstadoNombre(idPais, idEstado);
    }
}