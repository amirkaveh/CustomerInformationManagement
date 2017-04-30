package exception;

/**
 * Created by $Hamid on 4/29/2017.
 */
public class InputDataException extends Exception {

    public InputDataException() {
        super();
    }

    public InputDataException(Throwable cause) {
        super(cause);
    }

    public InputDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputDataException(String message) {
        super(message);
    }
}
