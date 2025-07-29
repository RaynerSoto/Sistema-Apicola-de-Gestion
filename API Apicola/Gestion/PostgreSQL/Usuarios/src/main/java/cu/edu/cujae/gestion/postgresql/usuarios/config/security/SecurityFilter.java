package cu.edu.cujae.gestion.postgresql.usuarios.config.security;

import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.token.TokenDto;
import cu.edu.cujae.gestion.postgresql.usuarios.core.repository.UsuarioRepository;
import cu.edu.cujae.gestion.postgresql.usuarios.utils.TokenUtils;
import cu.edu.cujae.gestion.postgresql.usuarios.utils.Validacion;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SecurityFilter{

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;

    @SneakyThrows
    //@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Inicio doFilter");
        TokenDto token = TokenUtils.requestToken(request);
        String requesUri = request.getRequestURI();
        System.out.println(requesUri);
        System.out.println(request.getHeader("Referer"));
        System.out.println(request.getHeader("Origin"));
        System.out.println(request.getRemotePort());
        System.out.println(request.getServerPort());
        System.out.println(request.getContextPath());
        if (token.getToken() != null){
            Validacion.validarElemento(token);
            token.setToken(token.getToken().replace("Bearer ", "").replaceAll(" ","").replaceAll("\"",""));
            var user = tokenService.getSubjetc(token.getToken());
            System.out.println(token);
            if(user != null){
                var usuario = usuarioRepository.findByUsernameEqualsIgnoreCase(user);
                //Forzar inicio de sesión
                System.out.println(usuario.get().getAuthorities());
                var autenticacion = new UsernamePasswordAuthenticationToken(usuario,null,usuario.get().getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(autenticacion);
            }
            else {
                throw new Exception("Usuario no encontrado");
            }
        }
        filterChain.doFilter(request, response);
    }
}
