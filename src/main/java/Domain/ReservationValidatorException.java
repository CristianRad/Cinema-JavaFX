package Domain;

public class ReservationValidatorException extends RuntimeException {

    public ReservationValidatorException(String message) {
        super("ReservationValidatorException: " + message);
    }

}
