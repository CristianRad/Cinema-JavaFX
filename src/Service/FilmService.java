package Service;

import Domain.Film;
import Repository.IRepository;

import java.util.ArrayList;
import java.util.List;

public class FilmService {

    private IRepository<Film> filmRepository;

    /**
     * Instantiates a service for films.
     * @param filmRepository is the repository used.
     */

    public FilmService(IRepository<Film> filmRepository) {
        this.filmRepository = filmRepository;
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
            if (film.getTitle().contains(text) || Integer.toString(film.getYear()).contains(text) ||
                Double.toString(film.getTicketPrice()).contains(text) || Boolean.toString(film.isOnScreen()).contains(text))
                    results.add(film);
        return results;
    }

    public IRepository<Film> getFilmRepository() { return filmRepository; }

}
