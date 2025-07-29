package cu.edu.cujae.gestion.postgresql.usuarios.utils;
import cu.edu.cujae.gestion.postgresql.usuarios.config.security.TokenService;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.token.TokenDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenUtils {
    // @Autowired private UsuarioServiceInterfaces usuarioService;
    @Autowired
    private TokenService tokenService;

    public static TokenDto requestToken(HttpServletRequest request){
        return new TokenDto(request.getHeader("Authorization"));
    }

    public static TokenDto requestTokenClean(TokenDto token){
        token.setToken(token.getToken().replace("Bearer ", "").replaceAll(" ","").replaceAll("\"",""));
        return token;
    }

    public static TokenDto requestTokenCleanHttp(HttpServletRequest request){
        return new TokenDto(request.getHeader("Authorization").replace("Bearer ", "").replaceAll(" ","").replaceAll("\"",""));
    }

    public String usernameToken(HttpServletRequest request){
        try {
            TokenDto token = requestTokenCleanHttp(request);
            var user = tokenService.getSubjetc(token.getToken());
            return user.toString();
        }catch(Exception e){
            return null;
        }
    }

    /*
    public UsuarioDto userToken(HttpServletRequest request){
       try{
           return new UsuarioDto(usuarioService.buscarUsuarioPorUsernameActivo(usernameToken(request)));
       }catch (Exception e){
           return null;
       }
    }*/
}
