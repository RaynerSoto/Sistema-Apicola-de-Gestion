package cu.edu.cujae.gestion.postgresql.usuarios.core.repository;

import cu.edu.cujae.gestion.postgresql.usuarios.core.conexion.microservicios.APIGeografia;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.paisEstado.PaisEstadoDtoComprobar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.paisEstado.PaisEstadoDtoID;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.edu.cujae.gestion.postgresql.usuarios.core.dto.usuario.UsuarioDtoRecibir;
import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.APIException;
import cu.edu.cujae.gestion.postgresql.usuarios.core.exception.SearchException;
import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Rol;
import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Sexo;
import cu.edu.cujae.gestion.postgresql.usuarios.core.model.Usuario;
import cu.edu.cujae.gestion.postgresql.usuarios.core.service.UsuarioService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UsuarioRepositoryTest {
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final SexoRepository sexoRepository;
    private final RolRepository rolRepository;

    @Autowired
    public UsuarioRepositoryTest(UsuarioService usuarioService, UsuarioRepository usuarioRepository, SexoRepository sexoRepository, RolRepository rolRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.sexoRepository = sexoRepository;
        this.rolRepository = rolRepository;
    }

    @Test
    public void insertUsuario() throws APIException, SearchException, IOException, InterruptedException {
        UsuarioDtoRecibir usuario = new UsuarioDtoRecibir("00101268327","Rayner Alejandro Soto Martínez","raynersoto","Prueba1234*","raynersoto01@gmail.com","La Habana","Cuba","calle 310","+5359682690","Masculino",false,false,"Administrador");
        PaisEstadoDtoID paisEstadoDtoID = (PaisEstadoDtoID) APIGeografia.comprobarPaisEstadoID(new PaisEstadoDtoComprobar(usuario.pais(), usuario.estado())).getRespuesta();
        Rol rol = rolRepository.findByNombreEquals(usuario.nombre());
        Sexo sexo = sexoRepository.findByNombreEquals(usuario.sexo());
        Usuario user = new Usuario(usuario, paisEstadoDtoID, rol, sexo);
        usuarioService.insertarUsuario(user);
    }

    @Test
    public void listarUsuarios(){
        RespuestaEstandar respuesta = new RespuestaEstandar(usuarioRepository.findAll());
        assertNotNull(respuesta);
    }
}