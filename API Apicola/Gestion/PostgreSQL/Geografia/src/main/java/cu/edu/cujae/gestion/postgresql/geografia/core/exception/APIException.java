package cu.edu.cujae.gestion.postgresql.geografia.core.exception;

/**
 * Es una excepción genérica para cualquier conexión con la API
 * que puede ocurrir durante la ejecución de operaciones relacionadas con la geografía.
 * Esta excepción se utiliza para manejar errores específicos de la API
 * y proporcionar información detallada sobre el problema.
 * @author Rayner Alejandro Soto Martínez
 * @version 1.0.0
 */
public class APIException extends Exception {
    public APIException() {
    }

    public APIException(String message) {
        super(message);
    }

    public APIException(String message, Throwable cause) {
        super(message, cause);
    }

    public APIException(Throwable cause) {
        super(cause);
    }

    public APIException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
