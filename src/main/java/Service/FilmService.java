package Service;

import Domain.Film;
import Domain.Reservation;
import Repository.IRepository;

import java.util.*;

public class FilmService {

    private IRepository<Film> filmRepository;
    private IRepository<Reservation> reservationRepository;
    private Stack<UndoRedoOperation<Film>> undoableOperations = new Stack();
    private Stack<UndoRedoOperation<Film>> redoableOperations = new Stack();

    /**
     * Instantiates a service for films.
     * @param filmRepository is the repository used.
     */

    public FilmService(IRepository<Film> filmRepository, IRepository<Reservation> reservationRepository) {
        this.filmRepository = filmRepository;
        this.reservationRepository = reservationRepository;
    }

    /**
     * Adds a film.
     * @param id is the ID of the film to add.
     * @param title is the title of the film to add.
     * @param year is the release year of the film to add.
     * @param ticketPrice is the price of the tickets for the film to add.
     * @param onScreen indicates whether the film to add is scheduled to run or not.
     * @throws FilmServiceException if the film with the ID to add already exists.
     */

    public void addFilm(String id, String title, int year, double ticketPrice, boolean onScreen) {
        Film film = new Film(id, title, year, ticketPrice, onScreen);
        if (filmRepository.getStorage().containsKey(id))
            throw new FilmServiceException(String.format("A film with the ID %s already exists!", id));
        filmRepository.insert(film);
        undoableOperations.add(new AddOperation<>(filmRepository, film));
        redoableOperations.clear();
    }

    /**
     * Updates a film.
     * @param id is the new ID of the film.
     * @param title is the new title of the film.
     * @param year is the new release year of the film.
     * @param ticketPrice is the new price of the tickets for the film.
     * @param onScreen indicates whether the film is scheduled to run or not.
     * @throws FilmServiceException if there is no film with the given ID to update.
     */

    public void updateFilm(String id, String title, int year, double ticketPrice, boolean onScreen) {
        Film film = new Film(id, title, year, ticketPrice, onScreen);
        if (!filmRepository.getStorage().containsKey(id))
            throw new FilmServiceException(String.format("There is no film with the ID %s!", id));
        undoableOperations.add(new UpdateOperation<>(filmRepository, filmRepository.findById(id), film));
        redoableOperations.add(new UpdateOperation<>(filmRepository, film, filmRepository.findById(id)));
        filmRepository.update(film);
    }

    /**
     * Removes a film.
     * @param id is the ID of the film to remove.
     * @throws FilmServiceException if there is no film with the given ID to remove.
     */

    public void removeFilm(String id) {
        if (!filmRepository.getStorage().containsKey(id))
            throw new FilmServiceException(String.format("There is no film with the ID %s!", id));
        undoableOperations.add(new RemoveOperation<>(filmRepository, filmRepository.findById(id)));
        redoableOperations.clear();
        filmRepository.remove(id);
    }

    /**
     * @return a list of all films.
     */

    public List<Film> getAllFilms() {
        return filmRepository.getAll();
    }

    /**
     * Searches films whose fields contain a given text.
     * @param text is the text searched for.
     * @return a list of films whose fields contain text.
     */

    public List<Film> fullTextSearch(String text) {
        List<Film> results = new ArrayList<>();
        for (Film film : getAllFilms())
            if (film.getTitle().toLowerCase().contains(text.toLowerCase()) || Integer.toString(film.getYear()).contains(text) ||
                Double.toString(film.getTicketPrice()).contains(text) || Boolean.toString(film.isOnScreen()).toLowerCase().contains(text.toLowerCase()))
                    results.add(film);
        return results;
    }

    public List<FilmReservationVM> getOrderedByReservations() {
        Map<String, Integer> frequencies = new HashMap<>();
        for (Reservation reservation : reservationRepository.getAll()) {
            String filmId = reservation.getIdFilm();
            if (frequencies.containsKey(filmId))
                frequencies.put(filmId, frequencies.get(filmId) + 1);
            else
                frequencies.put(filmId, 1);
        }

        List<FilmReservationVM> results = new ArrayList<>();
        for (String filmId : frequencies.keySet()) {
            Film film = filmRepository.findById(filmId);
            results.add(new FilmReservationVM(film, frequencies.get(filmId)));
        }
        results.sort((f1, f2) -> f2.reservations - f1. reservations);
        return results;
    }

    public void undo() {
        if (!undoableOperations.isEmpty()) {
            UndoRedoOperation<Film> lastOperation = undoableOperations.pop();
            lastOperation.doUndo();
            redoableOperations.add(lastOperation);
        }
    }

    public void redo() {
        if (!redoableOperations.isEmpty()) {
            UndoRedoOperation<Film> lastOperation = redoableOperations.pop();
            lastOperation.doRedo();
            undoableOperations.add(lastOperation);
        }
    }

    public IRepository<Film> getFilmRepository() { return filmRepository; }

}
