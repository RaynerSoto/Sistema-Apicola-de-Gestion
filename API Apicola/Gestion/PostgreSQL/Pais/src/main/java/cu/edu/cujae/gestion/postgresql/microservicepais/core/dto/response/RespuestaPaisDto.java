package cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.response;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.sent.PaisDtoSent;
import lombok.Data;

@Data
public class RespuestaPaisDto {
    //Esta parte del código indica que la respuesta fue aceptada cuando es true y si es false la petición fue rechazada por un error
    private boolean iscorrectoRespuesta;
    //Mensaje de reepuesta en caso de error
    private String respuesta;
    private PaisDtoSent paisDtoSent;

    public RespuestaPaisDto(boolean iscorrectoRespuesta,PaisDtoSent paisDtoSent) {
        this.iscorrectoRespuesta = iscorrectoRespuesta;
        this.respuesta = null;
        this.paisDtoSent = paisDtoSent;
    }

    public RespuestaPaisDto(boolean iscorrectoRespuesta, String respuesta) {
        this.iscorrectoRespuesta = iscorrectoRespuesta;
        this.respuesta = respuesta;
        this.paisDtoSent = null;
    }
}
