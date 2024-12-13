package cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.response;
import cu.edu.cujae.gestion.postgresql.microservicepais.core.dto.sent.PaisDtoSent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaPaisDtoList{
    //Esta parte del código indica que la respuesta fue aceptada cuando es true y si es false la petición fue rechazada por un error
    private boolean iscorrectoRespuesta;
    //Mensaje de reepuesta en caso de error
    private String respuesta;
    private List<PaisDtoSent> paisesDtoSent;

    public RespuestaPaisDtoList(boolean iscorrectoRespuesta, List<PaisDtoSent> paisesDtoSent) {
        this.iscorrectoRespuesta = iscorrectoRespuesta;
        this.respuesta = null;
        this.paisesDtoSent = paisesDtoSent;
    }

    public RespuestaPaisDtoList(boolean iscorrectoRespuesta, String mensaje) {
        this.iscorrectoRespuesta = iscorrectoRespuesta;
        this.respuesta = mensaje;
        this.paisesDtoSent = null;
    }
}
