package cu.edu.cujae.gestion.postgresql.microservicepais.core.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class Validacion {

    public static String comprobacionValidador(Object elemento){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        if (!validator.validate(elemento).isEmpty()){
            for (ConstraintViolation con : validator.validate(elemento)){
                return con.getMessage();
            }
        }
        return null;
    }

    public static void validarUnsupportedOperationException(Object elemento) throws NullPointerException{
        String validacion = Validacion.comprobacionValidador(elemento);
        if (!validacion.isBlank())
            throw new UnsupportedOperationException(validacion);
    }
}
