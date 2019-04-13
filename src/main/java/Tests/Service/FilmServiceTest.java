package Tests.Service;

import Domain.*;
import Repository.IRepository;
import Repository.InMemoryRepository;
import Service.FilmService;
import Service.FilmServiceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class FilmServiceTest {

    private IValidator<Film> filmValidator = new FilmValidator();
    private IRepository<Film> filmRepository = new InMemoryRepository<>(filmValidator);

    private IValidator<Reservation> reservationValidator = new ReservationValidator();
    private IRepository<Reservation> reservationRepository = new InMemoryRepository<>(reservationValidator);

    private FilmService filmService = new FilmService(filmRepository, reservationRepository);

    @Test
    public void add() {
        filmService.addFilm("1","Cars",2008,14.0,true);
        assertEquals(1, filmService.getAllFilms().size());

        try {
            filmService.addFilm("1","Cars",2008,14.0,true);
            fail ("Exception not thrown for duplicate film ID!");
        } catch (FilmServiceException error) {
            assertTrue(true);
        }
    }

    @Test
    public void update() {
        filmService.addFilm("2","X-Men",2004,25.0,false);
        filmService.updateFilm("2","X-Men",2004,25.0,true);

        assertTrue(filmService.getAllFilms().get(0).isOnScreen());
    }

    @Test
    public void remove() {
        filmService.addFilm("3","Hitch",2005,30.0,true);
        filmService.removeFilm("3");

        assertEquals(0, filmService.getAllFilms().size());
    }

    @Test
    public void getAll() {
        filmService.addFilm("4","Faster",2010,45.0,true);
        assertEquals(1, filmService.getAllFilms().size());
    }

}
