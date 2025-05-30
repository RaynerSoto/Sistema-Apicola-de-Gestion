package cu.edu.cujae.gestion.postgresql.geografia.core.serviceController.implement;

import cu.edu.cujae.gestion.postgresql.geografia.core.dto.pais.PaisDtoEnviar;
import cu.edu.cujae.gestion.postgresql.geografia.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.geografia.core.exception.SearchException;
import cu.edu.cujae.gestion.postgresql.geografia.core.model.Estado;
import cu.edu.cujae.gestion.postgresql.geografia.core.model.Pais;
import cu.edu.cujae.gestion.postgresql.geografia.core.repository.PaisRepository;
import cu.edu.cujae.gestion.postgresql.geografia.core.service.PaisService;
import cu.edu.cujae.gestion.postgresql.geografia.core.serviceController.PaisControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementación del servicio controlador para manejar las operaciones relacionadas con los países.
 * Proporciona métodos para obtener la lista de países y sus nombres.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0.0
 */
@Service
public class PaisControllerServiceImplement implements PaisControllerService {
    private final PaisRepository paisRepository;
    private final PaisService paisService;

    @Autowired
    public PaisControllerServiceImplement(PaisRepository paisRepository, PaisService paisService) {
        this.paisRepository = paisRepository;
        this.paisService = paisService;
    }

    /**
     * Obtiene la lista de países desde la base de datos.
     * @return ResponseEntity con la lista de países o un mensaje de error.
     */
    @Override
    public ResponseEntity<RespuestaEstandar> obtenerPaises() {
        try {
            return ResponseEntity.ok(new RespuestaEstandar(paisRepository.findAll().stream().map(PaisDtoEnviar::toPais)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RespuestaEstandar("No se pudo acceder al listado de países. Compruebe con el servicio técnico"));
        }
    }

    /**
     * Comprueba si un país existe en la base de datos por su nombre.
     * @return ResponseEntity con un mensaje de éxito o error.
     */
    @Override
    public ResponseEntity<RespuestaEstandar> obtenerPaisesNombre() {
        try {
            return ResponseEntity.ok(new RespuestaEstandar(paisRepository.findAll().stream().map(Pais::getNombre)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RespuestaEstandar("No se pudo acceder al listado de países. Compruebe con el servicio técnico"));
        }
    }
}
