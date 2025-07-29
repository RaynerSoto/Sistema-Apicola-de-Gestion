package cu.edu.cujae.gestion.postgresql.geografia.core.serviceController.implement;

import cu.edu.cujae.gestion.postgresql.geografia.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.geografia.core.service.ContinenteService;
import cu.edu.cujae.gestion.postgresql.geografia.core.serviceController.ContinenteControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio de controlador para manejar operaciones relacionadas con continentes.
 * @author Rayner Alejandro Soto Martínez
 * @version 0.1.0
 */
@Service
public class ContinenteControllerServiceImplement implements ContinenteControllerService {

    private ContinenteService continenteService;

    @Autowired
    public ContinenteControllerServiceImplement(ContinenteService continenteService) {
        this.continenteService = continenteService;
    }

    /**
     * Obtiene la lista de continentes.
     * @return ResponseEntity con la lista de continentes o un mensaje de error.
     */
    @Override
    public ResponseEntity<RespuestaEstandar> obtenerContinentes() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new RespuestaEstandar(continenteService.listarContinentes()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RespuestaEstandar("No se pudo acceder al listado de continente. Compruebe con el servicio técnico"));
        }
    }
}
