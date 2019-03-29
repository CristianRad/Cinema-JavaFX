package Domain;

public class FilmValidatorException extends RuntimeException {

    public FilmValidatorException(String message) {
        super("FilmValidatorException: " + message);
    }

}
