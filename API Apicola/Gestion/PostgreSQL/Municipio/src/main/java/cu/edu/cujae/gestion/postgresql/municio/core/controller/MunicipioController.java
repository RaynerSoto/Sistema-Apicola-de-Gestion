package cu.edu.cujae.gestion.postgresql.municio.core.controller;

import cu.edu.cujae.gestion.postgresql.municio.core.dto.recibir.PaisEstadoDto;
import cu.edu.cujae.gestion.postgresql.municio.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.municio.core.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/gestion/1/municipio")
public class MunicipioController {
    private MunicipioService municipioService;

    @Autowired
    public MunicipioController(MunicipioService municipioService) {
        this.municipioService = municipioService;
    }

    @PostMapping("/")
    public ResponseEntity<RespuestaEstandar> insertarMunicipios(@RequestBody PaisEstadoDto paisEstadoDto){
    }
}
