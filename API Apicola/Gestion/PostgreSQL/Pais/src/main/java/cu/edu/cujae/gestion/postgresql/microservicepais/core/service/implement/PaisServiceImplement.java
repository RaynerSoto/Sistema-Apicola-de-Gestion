package cu.edu.cujae.gestion.postgresql.microservicepais.core.service.implement;

import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.recibir.PaisDtoReci;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.sent.PaisDtoSent;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.mapping.Pais;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.openfeign.ContinenteOpenFeign;
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
    public void eliminarPais(Long id) throws Exception {
        if (paisRepository.existsById(id))
            paisRepository.deleteById(id);
        else
            throw new IllegalArgumentException("No se encuentra ningún país a eliminar");
    }

    @Override
    public PaisDtoSent buscarPais(Long id) {
        return PaisDtoSent.convertirPaisDtoSent(paisRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No se encontró el país buscado")
        ));
    }

    @Override
    public PaisDtoSent buscarPaisPorNombre(String nombre) {
        //Antes de proceder con este método se debe validar el anterior
        return null;
    }

    @Override
    public List<PaisDtoSent> buscarPaises() {
        return paisRepository.findAll().stream().map(p-> PaisDtoSent.convertirPaisDtoSent(p)).toList();

    }
}
