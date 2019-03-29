package Tests.Domain;

import Domain.*;
import Repository.IRepository;
import Repository.InMemoryRepository;
import Service.ReservationService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationTest {

    private IValidator<Film> filmValidator = new FilmValidator();
    private IValidator<Client> clientValidator = new ClientValidator();
    private IValidator<Reservation> reservationValidator = new ReservationValidator();

    private IRepository<Film> filmRepository = new InMemoryRepository<>(filmValidator);
    private IRepository<Client> clientRepository = new InMemoryRepository<>(clientValidator);
    private IRepository<Reservation> reservationRepository = new InMemoryRepository<>(reservationValidator);

    private ReservationService reservationService = new ReservationService(reservationRepository, clientRepository, filmRepository);

    private Film newFilm = new Film("5","American Pie",2001,50.0,true);
    private Client newClient = new Client("20","Doru","Bogdan","1880201124935", LocalDate.of(1988,2,1), LocalDate.of(2011,4,18),44);

    @Test
    public void getIdFilm() {
        filmRepository.insert(newFilm);
        clientRepository.insert(newClient);
        reservationService.addReservation("10","5","20", LocalDate.of(2018,11,11), LocalTime.of(16,45));

        assertEquals("5", reservationService.getAllReservations().get(0).getIdFilm());
    }

    public void setIdFilmIdClientDateTime() {
        filmRepository.insert(newFilm);
        clientRepository.insert(newClient);
        reservationService.addReservation("10","5","20", LocalDate.of(2018,11,11), LocalTime.of(16,45));

        reservationService.getAllReservations().get(0).setIdFilm("1");
        assertEquals("1", reservationService.getAllReservations().get(0).getIdFilm());

        reservationService.getAllReservations().get(0).setIdCardClient("1");
        assertEquals("1", reservationService.getAllReservations().get(0).getIdCardClient());

        reservationService.getAllReservations().get(0).setDate(LocalDate.of(2019,1,1));
        assertEquals("01.01.2019", reservationService.getAllReservations().get(0).getDate());

        reservationService.getAllReservations().get(0).setTime(LocalTime.of(18,0));
        assertEquals("18:00", reservationService.getAllReservations().get(0).getTime());
    }

}
