package Tests.Domain;

import Domain.*;
import Repository.IRepository;
import Repository.InMemoryRepository;
import Service.FilmService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FilmTest {

    private IValidator<Film> filmValidator = new FilmValidator();
    private IRepository<Film> filmRepository = new InMemoryRepository<>(filmValidator);

    private IValidator<Reservation> reservationValidator = new ReservationValidator();
    private IRepository<Reservation> reservationRepository = new InMemoryRepository<>(reservationValidator);

    private FilmService filmService = new FilmService(filmRepository, reservationRepository);

    @Test
    public void setTitleYearPriceOnScreen() {
        filmService.addFilm("1","Titanic",2000,20.0,true);

        filmService.getAllFilms().get(0).setTitle("The Lake House");
        assertEquals("The Lake House", filmService.getAllFilms().get(0).getTitle());

        filmService.getAllFilms().get(0).setYear(2003);
        assertEquals(2003, filmService.getAllFilms().get(0).getYear());

        filmService.getAllFilms().get(0).setTicketPrice(50.0);
        assertEquals(50.0, filmService.getAllFilms().get(0).getTicketPrice(), 0);

        filmService.getAllFilms().get(0).setOnScreen(false);
        assertFalse(filmService.getAllFilms().get(0).isOnScreen());
    }

}
