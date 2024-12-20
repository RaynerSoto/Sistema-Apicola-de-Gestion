package cu.edu.cujae.gestion.postgresql.microservicepais.core.utils;

import java.lang.reflect.Field;
import java.util.HashMap;

public class ConversionObjetos{
    
    public static <T> T convertirHashMapaObject(HashMap<String,Object> lista, Class<T> clase) throws Exception {
        T objeto = clase.getDeclaredConstructor().newInstance();
        for (Field f : clase.getDeclaredFields()){
            if(lista.containsKey(f.getName())){
                f.setAccessible(true);
                System.out.println(lista.get(f.getType()));
                if (f.getType().equals(long.class) || f.getType().equals(Long.class)) {
                    f.set(objeto, (Long)lista.get(f.getName()));
                }else {
                    f.set(objeto, lista.get(f.getName()));
                }

            }
        }
        return objeto;
    }
}
