package Tests.Repository;

import Domain.*;
import Repository.IRepository;
import Repository.InMemoryRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryRepositoryTest {

    private IValidator<Film> filmValidator = new FilmValidator();
    private IValidator<Client> clientValidator = new ClientValidator();
    private IValidator<Reservation> reservationValidator = new ReservationValidator();

    private IRepository<Film> filmRepository = new InMemoryRepository<>(filmValidator);
    private IRepository<Client> clientRepository = new InMemoryRepository<>(clientValidator);
    private IRepository<Reservation> reservationRepository = new InMemoryRepository<>(reservationValidator);

    private Film film = new Film("1","Heroes",1995,60.0,true);

    private Client client = new Client("10","Vlad","Rus","1234567891001", LocalDate.of(2000,7,22), LocalDate.of(2017,1,14),55);
    private Client newClient = new Client("10","Alin","Pop","1900712124938", LocalDate.of(1990,7,12), LocalDate.of(2014,3,17),80);

    private Reservation reservation = new Reservation("1","1","10", LocalDate.of(2012,10,12), LocalTime.of(15,0));

    @Test
    public void findFilmById() {
        filmRepository.insert(film);
        reservationRepository.insert(reservation);

        assertEquals(film, filmRepository.findById(reservation.getIdFilm()));
    }

    @Test
    public void insert() {
        filmRepository.insert(film);
        List<Film> allFilms = filmRepository.getAll();

        clientRepository.insert(client);
        List<Client> allClients = clientRepository.getAll();

        reservationRepository.insert(reservation);
        List<Reservation> allReservations = reservationRepository.getAll();

        assertEquals(1, allFilms.size());
        assertEquals(film, allFilms.get(0));

        assertEquals(1, allClients.size());
        assertEquals(client, allClients.get(0));

        assertEquals(1, allReservations.size());
        assertEquals(reservation, allReservations.get(0));
    }

    @Test
    public void update() {
        clientRepository.insert(client);
        clientRepository.update(newClient);
        List<Client> allClients = clientRepository.getAll();

        assertEquals(newClient, allClients.get(0));
        assertEquals(newClient, clientRepository.findById("10"));
    }

    @Test
    public void remove() {
        clientRepository.insert(client);
        clientRepository.remove("10");
        assertEquals(0, clientRepository.getAll().size());
    }

    @Test
    public void getAll() {
        filmRepository.insert(film);
        assertEquals(1, filmRepository.getAll().size());
    }

}
