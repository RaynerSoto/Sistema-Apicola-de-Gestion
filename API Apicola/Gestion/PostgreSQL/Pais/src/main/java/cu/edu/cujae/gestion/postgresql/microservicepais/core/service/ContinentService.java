package cu.edu.cujae.gestion.postgresql.microservicepais.core.service;

import java.sql.SQLException;

public interface ContinentService {
    public String obtenerContinente(String continent) throws Exception;
}
