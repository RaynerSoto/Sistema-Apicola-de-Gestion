package cu.edu.cujae.gestion.postgresql.microservicepais.core.controller;

import cu.edu.cujae.gestion.postgresql.microservicepais.core.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/gestion/1/pais")
public class PaisController {

    private PaisService paisService;

    @Autowired
    public PaisController(PaisService paisService) {
        this.paisService = paisService;
    }
    
    @GetMapping("/")
    public ResponseEntity<?> listadoPaises(){
        
    }
}
