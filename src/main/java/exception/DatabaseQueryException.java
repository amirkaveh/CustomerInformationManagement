package exception;

/**
 * Created by $Hamid on 4/29/2017.
 */
public class DatabaseQueryException extends Exception {
    public DatabaseQueryException() {
        super();
    }

    public DatabaseQueryException(String message) {
        super(message);
    }

    public DatabaseQueryException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseQueryException(Throwable cause) {
        super(cause);
    }
}
