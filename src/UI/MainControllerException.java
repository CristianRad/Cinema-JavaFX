package UI;

public class MainControllerException extends RuntimeException {

    public MainControllerException(String message) {
        super("MainControllerException: " + message);
    }

}
