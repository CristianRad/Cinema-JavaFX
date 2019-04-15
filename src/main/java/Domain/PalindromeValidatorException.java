package Domain;

public class PalindromeValidatorException extends RuntimeException {

    public PalindromeValidatorException(String message) {
        super("PalindromeValidatorException: " + message);
    }

}
