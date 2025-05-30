package cu.edu.cujae.gestion.postgresql.geografia.core.serviceController.implement;

import cu.edu.cujae.gestion.postgresql.geografia.core.dto.estado.EstadoDtoEnviar;
import cu.edu.cujae.gestion.postgresql.geografia.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.geografia.core.repository.EstadoRepository;
import cu.edu.cujae.gestion.postgresql.geografia.core.service.EstadoService;
import cu.edu.cujae.gestion.postgresql.geografia.core.serviceController.EstadoControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio controlador para manejar las operaciones relacionadas con los estados.
 * Proporciona métodos para obtener los estados de un país dado su nombre.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0.0
 */
@Service
public class EstadoControllerServiceImplement implements EstadoControllerService {
    private final EstadoService estadoService;
    private final EstadoRepository estadoRepository;

    @Autowired
    public EstadoControllerServiceImplement(EstadoService estadoService, EstadoRepository estadoRepository) {
        this.estadoService = estadoService;
        this.estadoRepository = estadoRepository;
    }

    /**
     * Obtiene los estados de un país dado su nombre.
     * @param nombrePais
     * @return ResponseEntity con una lista de estados del país especificado.
     */
    @Override
    public ResponseEntity<RespuestaEstandar> obtenerEstados(String nombrePais) {
        return ResponseEntity.ok(new RespuestaEstandar(estadoRepository.findAll().stream().filter(estado -> estado.getPais().getNombre().equals(nombrePais)).map(EstadoDtoEnviar::toEstado)));
    }
}
