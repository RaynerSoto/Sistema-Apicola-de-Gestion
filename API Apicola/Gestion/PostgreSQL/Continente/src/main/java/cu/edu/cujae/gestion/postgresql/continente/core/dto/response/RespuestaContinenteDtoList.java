package cu.edu.cujae.gestion.postgresql.continente.core.dto.response;
import cu.edu.cujae.gestion.postgresql.continente.core.dto.standar.ContinenteDto;
import lombok.Data;

import java.util.List;

@Data
public class RespuestaContinenteDtoList{
    //Esta parte del código indica que la respuesta fue aceptada cuando es true y si es false la petición fue rechazada por un error
    private boolean iscorrectoRespuesta;
    //Mensaje de reepuesta en caso de error
    private String respuesta;
    private List<ContinenteDto> continentes;

    public RespuestaContinenteDtoList(boolean iscorrectoRespuesta,List<ContinenteDto> continentes) {
        this.iscorrectoRespuesta = iscorrectoRespuesta;
        this.respuesta = null;
        this.continentes = continentes;
    }

    public RespuestaContinenteDtoList(boolean iscorrectoRespuesta, String respuesta) {
        this.iscorrectoRespuesta = iscorrectoRespuesta;
        this.respuesta = respuesta;
        this.continentes = null;
    }
}
