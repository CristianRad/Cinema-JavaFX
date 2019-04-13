package Service;

public class FilmServiceException extends RuntimeException {

    public FilmServiceException(String message) {
        super("FilmServiceException: " + message);
    }

}
