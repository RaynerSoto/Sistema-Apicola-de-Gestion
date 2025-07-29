package cu.edu.cujae.gestion.postgresql.geografia.core.exception;

/**
 * Esta es una excepción para la conversión entre objetos
 * que puede ocurrir durante la ejecución de operaciones relacionadas con la geografía.
 * Esta excepción se utiliza para manejar errores específicos de asignación
 * y proporcionar información detallada sobre el problema.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0.0
 */
public class AsignacionException extends Exception{
    public AsignacionException() {
    }

    public AsignacionException(String message) {
        super(message);
    }

    public AsignacionException(String message, Throwable cause) {
        super(message, cause);
    }

    public AsignacionException(Throwable cause) {
        super(cause);
    }

    public AsignacionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
