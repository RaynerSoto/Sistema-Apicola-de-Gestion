package cu.edu.cujae.gestion.postgresql.geografia.core.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Clase de utilidades para la validación de elementos utilizando Jakarta Bean Validation.
 * Proporciona métodos para listar las validaciones no cumplidas y validar un elemento.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0
 */
public class Validacion{
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private Validacion(){}

    public static <T> Set<String> listarValidacionesNoCumplidastoSet(T elemento){
        return validator.validate(elemento).
                stream().map(con -> con.getMessage())
                .collect(Collectors.toSet());
    }

    public static <T> List<String> listarValidacionesNoCumplidastoList(T elemento){
        return validator.validate(elemento).
                stream().map(con -> con.getMessage())
                .toList();
    }

    /**
     * @apiNote {@summary = Función de validación de los elementos}
     * @exception UnsupportedOperationException
     * @param elemento
     */
    public static <T> void validarElemento(T elemento){
        for(ConstraintViolation con : validator.validate(elemento)){
            throw new UnsupportedOperationException(con.getMessage());
        }
    }
}
