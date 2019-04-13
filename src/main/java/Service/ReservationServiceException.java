package Service;

public class ReservationServiceException extends RuntimeException {

    public ReservationServiceException(String message) {
        super("ReservationServiceException: " + message);
    }

}
