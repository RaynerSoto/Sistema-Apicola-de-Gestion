package cu.edu.cujae.gestion.postgresql.geografia.utils;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Clase de utilidad para convertir un HashMap<String, Object> a un objeto de una clase específica.
 * Utiliza reflexión para asignar los valores del HashMap a los campos del objeto.
 *
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
public class ConversionObjetos{

    private ConversionObjetos() {}

    public static <T> T convertirHashMapaObject(HashMap<String,Object> lista, Class<T> clase) throws Exception {
        T objeto = clase.getDeclaredConstructor().newInstance();
        for (Field f : clase.getDeclaredFields()){
            if(lista.containsKey(f.getName())){
                f.setAccessible(true);
                if ((f.getType().equals(long.class) || f.getType().equals(Long.class)) && lista.get(f.getName()) instanceof Integer) {
                    f.set(objeto,Long.valueOf((String) lista.get(f.getName())));
                }else if ((f.getType().equals(double.class) || f.getType().equals(Double.class)) && lista.get(f.getName()) instanceof Float) {
                    f.set(objeto, Double.valueOf((String) lista.get(f.getName())));
                }else {
                    f.set(objeto, lista.get(f.getName()));
                }
            }
        }
        return objeto;
    }
}
