package cu.edu.cujae.gestion.postgresql.microservicepais.core.service.implement;

import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.pais.enviar.PaisDtoSent;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.exception.SearchException;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.model.Pais;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.conexion.openfeign.ContinenteOpenFeign;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.repository.PaisRepository;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisServiceImplement implements PaisService {
    private final PaisRepository paisRepository;
    private final ContinenteOpenFeign continenteOpenFeign;

    @Autowired
    public PaisServiceImplement(PaisRepository paisRepository, ContinenteOpenFeign continenteOpenFeign) {
        this.paisRepository = paisRepository;
        this.continenteOpenFeign = continenteOpenFeign;
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

    @Override
    public PaisDtoSent buscarPais(Long id) {
        return PaisDtoSent.convertirPaisDtoSent(paisRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No se encontró el país buscado")
        ));
    }

    @Override
    public PaisDtoSent buscarPaisPorNombre(String nombre) throws SearchException {
        if (paisRepository.existsPaisByNombre(nombre))
            return PaisDtoSent.convertirPaisDtoSent(paisRepository.findByNombreEquals(nombre).get());
        throw new SearchException("No se ha podido encontrar el país por su nombre");
    }

    @Override
    public List<PaisDtoSent> buscarPaises() {
        return paisRepository.findAll().stream().map(PaisDtoSent::convertirPaisDtoSent).toList();
    }
}
