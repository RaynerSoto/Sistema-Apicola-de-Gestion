package cu.edu.cujae.gestion.postgresql.municio.core.controller;

import cu.edu.cujae.gestion.postgresql.municio.core.conexion.apiExterna.APIGeograficaMunicipio;
import cu.edu.cujae.gestion.postgresql.municio.core.dto.apiGeografica.APIGeoMunicipioDto;
import cu.edu.cujae.gestion.postgresql.municio.core.dto.apiGeografica.GeonamesDto;
import cu.edu.cujae.gestion.postgresql.municio.core.dto.recibir.PaisEstadoDto;
import cu.edu.cujae.gestion.postgresql.municio.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.municio.core.exception.APIException;
import cu.edu.cujae.gestion.postgresql.municio.core.model.Municipio;
import cu.edu.cujae.gestion.postgresql.municio.core.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/gestion/1/municipio")
public class MunicipioController {
    private MunicipioService municipioService;

    @Autowired
    public MunicipioController(MunicipioService municipioService) {
        this.municipioService = municipioService;
    }

    @PostMapping("/")
    public ResponseEntity<RespuestaEstandar> insertarMunicipios(@RequestBody PaisEstadoDto paisEstadoDto, Principal principal) throws APIException, IOException, InterruptedException {
        APIGeoMunicipioDto apiGeoMunicipioDto = APIGeograficaMunicipio.obtenerDatosMunicipio(paisEstadoDto);
        List<GeonamesDto> geonamesDtos = apiGeoMunicipioDto.geonames().stream().map(municipio -> {
            try {
                municipioService.insertarMunicipio(new Municipio());
                return null;
            }catch (Exception e){
                return municipio;
            }
        }).filter(Objects::nonNull).toList();
        if (geonamesDtos.isEmpty())
            return ResponseEntity.ok(new RespuestaEstandar("Insertados " + apiGeoMunicipioDto.totalResultsCount() + " municipios"));
        else
            return ResponseEntity.ok(new RespuestaEstandar("Insertados " + (apiGeoMunicipioDto.totalResultsCount() - geonamesDtos.size())  + " municipios. Los fallos han sido" + geonamesDtos));
    }
}
