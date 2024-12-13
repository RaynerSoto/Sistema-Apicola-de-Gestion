package cu.edu.cujae.gestion.postgresql.continente.core.dto.response;
import cu.edu.cujae.gestion.postgresql.continente.core.dto.standar.ContinenteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaContinenteDto{
    //Esta parte del código indica que la respuesta fue aceptada cuando es true y si es false la petición fue rechazada por un error
    private boolean iscorrectoRespuesta;
    //Mensaje de reepuesta en caso de error
    private String respuesta;
    //Almacenar cuando objeto que devuelva la respuesta
    private ContinenteDto continenteDto;

    public RespuestaContinenteDto(boolean isCorrect,ContinenteDto respuesta) {
        this.iscorrectoRespuesta = isCorrect;
        this.respuesta = null;
        this.continenteDto = respuesta;
    }

    public RespuestaContinenteDto(boolean isCorrect,String message) {
        this.iscorrectoRespuesta = isCorrect;
        this.respuesta = message;
        this.continenteDto = null;
    }
}
