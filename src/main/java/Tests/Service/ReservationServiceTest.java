package Tests.Service;

import Domain.*;
import Repository.IRepository;
import Repository.InMemoryRepository;
import Service.ReservationService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationServiceTest {

    private IValidator<Film> filmValidator = new FilmValidator();
    private IValidator<Client> clientValidator = new ClientValidator();
    private IValidator<Reservation> reservationValidator = new ReservationValidator();

    private IRepository<Film> filmRepository = new InMemoryRepository<>(filmValidator);
    private IRepository<Client> clientRepository = new InMemoryRepository<>(clientValidator);
    private IRepository<Reservation> reservationRepository = new InMemoryRepository<>(reservationValidator);

    private ReservationService reservationService = new ReservationService(reservationRepository, clientRepository, filmRepository);

    private Film film = new Film("1","Cars",2008,14.0,true);
    private Film sameFilm = new Film("1","Cars",2008,14.0,true);
    private Client client = new Client("1","Robert","Bura","1910909126212", LocalDate.of(1991,9,9), LocalDate.of(2013,10,14),43);

    @Test
    public void add() {
        filmRepository.insert(film);
        clientRepository.insert(client);
        reservationService.addReservation("1","1","1", LocalDate.of(2017,12,22), LocalTime.of(19,45));

        assertEquals(1, reservationService.getAllReservations().size());
    }

    @Test
    public void update() {
        filmRepository.insert(film);
        clientRepository.insert(client);
        reservationService.addReservation("1","1","1", LocalDate.of(2017,12,22), LocalTime.of(19,45));
        reservationService.updateReservation("1","1","1", LocalDate.of(2017,12,22), LocalTime.of(21,0));

        assertEquals("21:00", reservationService.getAllReservations().get(0).getTime());
    }

    @Test
    public void remove() {
        filmRepository.insert(film);
        clientRepository.insert(client);
        reservationService.addReservation("1","1","1", LocalDate.of(2017,12,22), LocalTime.of(19,45));
        reservationService.removeReservation("1");

        assertEquals(0, reservationService.getAllReservations().size());
    }

    @Test
    public void getAll() {
        filmRepository.insert(film);
        clientRepository.insert(client);
        reservationService.addReservation("1","1","1", LocalDate.of(2017,12,7), LocalTime.of(17,0));

        assertEquals(1, reservationService.getAllReservations().size());
    }

    @Test
    public void fullTextSearch() {
        filmRepository.insert(film);
        clientRepository.insert(client);
        reservationService.addReservation("1","1","1", LocalDate.of(2017,12,22), LocalTime.of(19,45));
        String text = "19";

        assertEquals(1, reservationService.fullTextSearch(text).size());
    }

}
