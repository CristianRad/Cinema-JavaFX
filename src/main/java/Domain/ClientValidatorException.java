package Domain;

public class ClientValidatorException extends RuntimeException {

    public ClientValidatorException(String message) {
        super("ClientValidatorException: " + message);
    }

}
