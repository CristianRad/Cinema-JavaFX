package Service;

import Domain.Client;
import Domain.Film;
import Domain.Reservation;
import Repository.IRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {

    private IRepository<Reservation> reservationRepository;
    private IRepository<Client> clientRepository;
    private IRepository<Film> filmRepository;

    /**
     * Instantiates a service for reservations.
     * @param reservationRepository is the repository used.
     */

    public ReservationService(IRepository<Reservation> reservationRepository, IRepository<Client> clientRepository, IRepository<Film> filmRepository) {
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
        this.filmRepository = filmRepository;
    }

    /**
     * Adds a reservation.
     * Adds 10% of the film's ticket price bonus points to the client's card.
     * @param id is the ID of the reservation.
     * @param idFilm is the ID of the film on the reservation.
     * @param idCardClient is the ID of the client on the reservation.
     * @param date is the date when the reservation was made.
     * @param time is the time when the reservation was made.
     * @throws ReservationServiceException if the film's ID does not exist or the film is not scheduled to run.
     */

    public void addReservation(String id, String idFilm, String idCardClient, LocalDate date, LocalTime time) {
        Reservation reservation = new Reservation(id, idFilm, idCardClient, date, time);
        if (reservationRepository.getStorage().containsKey(id))
            throw new ReservationServiceException(String.format("A reservation with the ID %s already exists!", id));

        Film bookedFilm = filmRepository.findById(idFilm);
        if (bookedFilm == null)
            throw new ReservationServiceException(String.format("There is no film with the ID %s!", idFilm));
        if (!bookedFilm.isOnScreen())
            throw new ReservationServiceException("The film is not scheduled to run!");

        reservationRepository.insert(reservation);

        Client cardClient = clientRepository.findById(idCardClient);
        if (cardClient != null)
            cardClient.setPoints(cardClient.getPoints() + (int)bookedFilm.getTicketPrice() / 10);
    }

    /**
     * Updates a reservation.
     * @param id is the new ID of the reservation.
     * @param idFilm is the new ID of the film on the reservation.
     * @param idCardClient is the new ID of the client on the reservation.
     * @param date is the new date when the reservation was made.
     * @param time is the new time when the reservation was made.
     * @throws ReservationServiceException if there is no reservation with the given ID to update.
     */

    public void updateReservation(String id, String idFilm, String idCardClient, LocalDate date, LocalTime time) {
        Reservation reservation = new Reservation(id, idFilm, idCardClient, date, time);
        if (!reservationRepository.getStorage().containsKey(id))
            throw new ReservationServiceException(String.format("There is no reservation with the ID %s!", id));
        reservationRepository.update(reservation);
    }

    /**
     * Removes a reservation.
     * @param id is the ID of the reservation to remove.
     * @throws ReservationServiceException if there is no reservation with the given ID to remove.
     */

    public void removeReservation(String id) {
        if (!reservationRepository.getStorage().containsKey(id))
            throw new ReservationServiceException(String.format("There is no reservation with the ID %s!", id));
        reservationRepository.remove(id);
    }

    /**
     * @return a list of all reservations.
     */

    public List<Reservation> getAllReservations() {
        return reservationRepository.getAll();
    }

    /**
     * Searches reservations whose fields contain a given text.
     * @param text is the text searched for.
     * @return a list of reservations whose fields contain text.
     */

    public List<Reservation> fullTextSearch(String text) {
        List<Reservation> results = new ArrayList<>();
        for (Reservation reservation : getAllReservations())
            if (reservation.getIdFilm().contains(text) || reservation.getIdCardClient().contains(text) ||
                reservation.getDate().toString().contains(text) || reservation.getTime().toString().contains(text))
                    results.add(reservation);
        return results;
    }

}
