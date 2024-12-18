package cu.edu.cujae.gestion.postgresql.continente.core.utils;

import cu.edu.cujae.gestion.postgresql.continente.core.model.Continente;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MapperTest {
    @Test
    public void map() {
        Continente continente = new Continente();
        for(Object v : continente.getClass().getDeclaredFields()){
            System.out.println(v);
        }
    }

}