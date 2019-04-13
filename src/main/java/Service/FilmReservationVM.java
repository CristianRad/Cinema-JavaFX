package Service;

import Domain.Film;

public class FilmReservationVM {

    public Film film;
    public int reservations;

    public String id;
    public String title;
    public int year;
    public double price;
    public boolean onScreen;

    public FilmReservationVM(Film film, int reservations) {
        this.film = film;
        this.reservations = reservations;
    }

    public int getReservations() {
        return reservations;
    }

    public String getId() {
        return film.getId();
    }

    public String getTitle() {
        return film.getTitle();
    }

    public int getYear() {
        return film.getYear();
    }

    public double getPrice() {
        return film.getTicketPrice();
    }

    public boolean isOnScreen() {
        return film.isOnScreen();
    }

}
