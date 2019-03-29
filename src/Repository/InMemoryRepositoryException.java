package Repository;

public class InMemoryRepositoryException extends RuntimeException {

    public InMemoryRepositoryException(String message) {
        super("InMemoryRepositoryException: " + message);
    }

}
