package cu.edu.cujae.gestion.postgresql.geografia.core.service.implement;
import cu.edu.cujae.gestion.postgresql.geografia.core.exception.SearchException;
import cu.edu.cujae.gestion.postgresql.geografia.core.model.Pais;
import cu.edu.cujae.gestion.postgresql.geografia.core.repository.PaisRepository;
import cu.edu.cujae.gestion.postgresql.geografia.core.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio PaisService.
 * Proporciona métodos para insertar, modificar, actualizar y eliminar países en la base de datos.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
@Service
public class PaisServiceImplement implements PaisService {
    private final PaisRepository paisRepository;

    @Autowired
    public PaisServiceImplement(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    @Override
    public void insertarPais(Pais pais) {
        paisRepository.save(pais);
    }

    @Override
    public void modificarPais(Pais pais) throws Exception {
        if (paisRepository.existsById(pais.getIdPais()))
            paisRepository.save(pais);
        else
            throw new IllegalArgumentException("No se encuentra ningún país a modificar");
    }

    @Override
    public void actualizarPais(Pais pais) throws Exception {
        if(paisRepository.existsPaisByNombre(pais.getNombre())){
            pais.setIdPais(paisRepository.findByNombreEquals(pais.getNombre()).get().getIdPais());
            paisRepository.save(pais);
        }
        else {
            throw new SearchException("No se encontró ningún país a modificar");
        }
    }

    @Override
    public void eliminarPais(Long id) throws Exception {
        if (paisRepository.existsById(id))
            paisRepository.deleteById(id);
        else
            throw new SearchException("No se encuentra ningún país a eliminar");
    }
}
