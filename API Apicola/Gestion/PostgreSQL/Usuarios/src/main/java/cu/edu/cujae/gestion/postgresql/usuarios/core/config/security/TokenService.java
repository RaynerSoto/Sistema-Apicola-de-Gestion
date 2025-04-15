package cu.edu.cujae.gestion.postgresql.usuarios.core.config.security;
import com.auth0.jwt.exceptions.JWTCreationException;
import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Usuario;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256("${jwt.secreto}");
            String token = JWT.create()
                    .withIssuer("Sistema Apicola de Gestión")
                    .withClaim("id",usuario.getIdUsuario())
                    .withSubject(usuario.getUsername())
                    .withClaim("Rol",usuario.getRol().getRol_nombre())
                    .withClaim("correo",usuario.getEmail())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException(exception);
        }
    }

    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-05:00"));
    }

    public String getSubjetc(String token) throws Exception {
        if(token == null)
            throw new Exception("Token nullo");
        DecodedJWT decodedJWT = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("${jwt.secreto}");
            JWTVerifier verifier = JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("Sistema Apicola de Gestión")
                    // reusable verifier instance
                    .build();
            decodedJWT = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            throw new Exception("No se puede validar el token");
        }
        if (decodedJWT.getSubject() == null)
            throw new RuntimeException("No se puede verificar el token");
        return decodedJWT.getSubject();
    }

    public boolean isActivoToken(String token) throws Exception{
        try {
            String persona = getSubjetc(token);
            if (persona == null || persona.equals(""))
                return false;
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
