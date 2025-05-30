package cu.edu.cujae.gestion.postgresql.geografia.core.serviceController.implement;

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
import cu.edu.cujae.gestion.postgresql.geografia.core.exception.SearchException;
import cu.edu.cujae.gestion.postgresql.geografia.core.model.Estado;
import cu.edu.cujae.gestion.postgresql.geografia.core.model.Pais;
import cu.edu.cujae.gestion.postgresql.geografia.core.repository.EstadoRepository;
import cu.edu.cujae.gestion.postgresql.geografia.core.repository.PaisRepository;
import cu.edu.cujae.gestion.postgresql.geografia.core.service.ContinenteService;
import cu.edu.cujae.gestion.postgresql.geografia.core.service.EstadoService;
import cu.edu.cujae.gestion.postgresql.geografia.core.service.MunicipioService;
import cu.edu.cujae.gestion.postgresql.geografia.core.service.PaisService;
import cu.edu.cujae.gestion.postgresql.geografia.core.serviceController.GeografiaControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.management.OperationsException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Implementación del servicio de controlador para manejar operaciones relacionadas con la geografía.
 * Proporciona métodos para insertar datos geográficos, comprobar la existencia de un país y estado,
 * y obtener información de país y estado por ID o nombre.
 * @author Rayner Alejandro Soto Martínez
 * @version 0.1.0
 */
@Service
public class GeografiaControllerServiceImplement implements GeografiaControllerService {
    private final PaisService paisService;
    private final ContinenteService continenteService;
    private final MunicipioService municipioService;
    private final EstadoService estadoService;
    private final EstadoRepository estadoRepository;
    private final PaisRepository paisRepository;

    @Autowired
    public GeografiaControllerServiceImplement(PaisService paisService, ContinenteService continenteService, MunicipioService municipioService, EstadoService estadoService, EstadoRepository estadoRepository, PaisRepository paisRepository) {
        this.paisService = paisService;
        this.continenteService = continenteService;
        this.municipioService = municipioService;
        this.estadoService = estadoService;
        this.estadoRepository = estadoRepository;
        this.paisRepository = paisRepository;
    }

    /**
     * Inserta datos geográficos de un país y sus estados en la base de datos.
     * @param nombre nombre del país a insertar.
     * @return ResponseEntity con un mensaje de éxito o error.
     * @throws Exception
     */
    @Override
    public ResponseEntity<RespuestaEstandar> insertarDatosGeograficos(String nombre) throws Exception {
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
     * Comprueba si un país y un estado existen en la base de datos.
     * @param paisEstado que es un objeto contiene el nombre del país y del estado a comprobar.
     * @return ResponseEntity con un mensaje de éxito o error.
     */
    @Override
    @Deprecated
    public ResponseEntity<RespuestaEstandar> comprobarPaisEstado(PaisEstadoDtoComprobar paisEstado) {
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
     * Obtiene un país y un estado por su nombre.
     * @param paisEstado que es un objeto contiene el nombre del país y del estado a buscar.
     * @return ResponseEntity con el ID del país y del estado.
     * @throws Exception
     */
    @Override
    public ResponseEntity<RespuestaEstandar> obtenerPaisEstadoID(PaisEstadoDtoComprobar paisEstado) throws Exception {
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
     * Obtiene el nombre de un país y un estado por sus IDs.
     * @param idPais que es el ID del país a buscar.
     * @param idEstado que es el ID del estado a buscar.
     * @return ResponseEntity con el nombre del país y del estado.
     * @throws Exception
     */
    @Override
    public ResponseEntity<RespuestaEstandar> obtenerPaisEstadoNombre(Long idPais, Long idEstado) throws Exception {
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
