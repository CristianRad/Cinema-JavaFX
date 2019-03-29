package Tests.Domain;

import Domain.Film;
import Domain.FilmValidator;
import Domain.IValidator;
import Repository.InMemoryRepositoryException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilmValidatorTest {

    private IValidator<Film> filmIValidator = new FilmValidator();

    @Test
    public void validate() {
        Film film = new Film("3","The Mechanic",2020,35.0,true);
        try {
            filmIValidator.validate(film);
        } catch (InMemoryRepositoryException error) {
            assertTrue(true);
        }
    }

}
