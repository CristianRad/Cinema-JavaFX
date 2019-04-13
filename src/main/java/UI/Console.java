package UI;

import Domain.Client;
import Domain.Film;
import Domain.Reservation;
import Service.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Console {

    private FilmService filmService;
    private ClientService clientService;
    private ReservationService reservationService;
    private Scanner scanner;

    public Console(FilmService filmService, ClientService clientService, ReservationService reservationService) {
        this.filmService = filmService;
        this.clientService = clientService;
        this.reservationService = reservationService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println("1. CRUD Films");
        System.out.println("2. CRUD Clients");
        System.out.println("3. CRUD Reservations");
        System.out.println("x. Exit");
    }

    public void showMenuFilms() {
        System.out.println("1. Add Film");
        System.out.println("2. Update Film");
        System.out.println("3. Remove Film");
        System.out.println("4. Show All Films");
        System.out.println("5. Search Films");
        System.out.println("x. Go Back");
    }

    public void showMenuClients() {
        System.out.println("1. Add Client");
        System.out.println("2. Update Client");
        System.out.println("3. Remove Client");
        System.out.println("4. Show All Clients");
        System.out.println("5. Search Clients");
        System.out.println("x. Go Back");
    }

    public void showMenuReservations() {
        System.out.println("1. Add Reservation");
        System.out.println("2. Update Reservation");
        System.out.println("3. Remove Reservation");
        System.out.println("4. Show All Reservations");
        System.out.println("5. Search Reservations");
        System.out.println("x. Go Back");
    }

    public void run() {
        while (true) {
            showMenu();
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    runFilms();
                    break;
                case "2":
                    runClients();
                    break;
                case "3":
                    runReservations();
                    break;
                case "x":
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private void runFilms() {
        while (true) {
            showMenuFilms();
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    handleAddFilm();
                    break;
                case "2":
                    handleUpdateFilm();
                    break;
                case "3":
                    handleRemoveFilm();
                    break;
                case "4":
                    handleShowAllFilms();
                    break;
                case "5":
                    runFilmsSearch();
                    break;
                case "x":
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private void handleAddFilm() {
        try {
            System.out.print("Enter film ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter film title: ");
            String title = scanner.nextLine();
            System.out.print("Enter release year: ");
            int year = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter ticket price: ");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Is it on screen? (true/false): ");
            boolean onScreen = Boolean.parseBoolean(scanner.nextLine());

            filmService.addFilm(id, title, year, price, onScreen);
            System.out.println("Film added successfully!");
        } catch (FilmServiceException error) {
            System.out.println("Errors: " + error.getMessage());
        }
    }

    private void handleUpdateFilm() {
        try {
            System.out.print("Enter film ID to update: ");
            String id = scanner.nextLine();
            System.out.print("Enter new film title: ");
            String title = scanner.nextLine();
            System.out.print("Enter new release year: ");
            int year = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter new ticket price: ");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Is the new film on screen? (true/false): ");
            boolean onScreen = Boolean.parseBoolean(scanner.nextLine());

            filmService.updateFilm(id, title, year, price, onScreen);
            System.out.println("Film updated successfully!");
        } catch (FilmServiceException error) {
            System.out.println("Errors: " + error.getMessage());
        }
    }

    private void handleRemoveFilm() {
        try {
            System.out.print("Enter the ID of the film to remove: ");
            String id = scanner.nextLine();

            filmService.removeFilm(id);
            System.out.println("Film removed successfully!");
        } catch (FilmServiceException error) {
            System.out.println("Errors: " + error.getMessage());
        }
    }

    private void handleShowAllFilms() {
        for (Film film : filmService.getAllFilms())
            System.out.println(film);
    }

    private void runFilmsSearch() {
        System.out.println("Dati cautarea:");
        String text = scanner.nextLine();
        System.out.println("Rezultatele cautarii sunt:");
        for (Film film : filmService.fullTextSearch(text))
            System.out.println(film);
    }

    private void runClients() {
        while (true) {
            showMenuClients();
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    handleAddClient();
                    break;
                case "2":
                    handleUpdateClient();
                    break;
                case "3":
                    handleRemoveClient();
                    break;
                case "4":
                    handleShowAllClients();
                    break;
                case "5":
                    runClientsSearch();
                    break;
                case "x":
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private void handleAddClient() {
        try {
            System.out.print("Enter client ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter client name: ");
            String name = scanner.nextLine();
            System.out.print("Enter client surname: ");
            String surname = scanner.nextLine();
            System.out.print("Enter client CNP: ");
            String cnp = scanner.nextLine();
            System.out.print("Enter client day of birth: ");
            int dayOfBirth = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter client month of birth: ");
            int monthOfBirth = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter client year of birth: ");
            int yearOfBirth = Integer.parseInt(scanner.nextLine());
            LocalDate birthday = LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth);
            System.out.print("Enter client day of registration: ");
            int dayOfRegistration = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter client month of registration: ");
            int monthOfRegistration = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter client year of registration: ");
            int yearOfRegistration = Integer.parseInt(scanner.nextLine());
            LocalDate registrationDay = LocalDate.of(yearOfRegistration, monthOfRegistration, dayOfRegistration);
            System.out.print("Enter client points: ");
            int points = Integer.parseInt(scanner.nextLine());

            clientService.addClient(id, name, surname, cnp, birthday, registrationDay, points);
            System.out.println("Client added successfully!");
        } catch (ClientServiceException error) {
            System.out.println("Errors: " + error.getMessage());
        }
    }

    private void handleUpdateClient() {
        try {
            System.out.print("Enter new client ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter new client name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new client surname: ");
            String surname = scanner.nextLine();
            System.out.print("Enter new client CNP: ");
            String cnp = scanner.nextLine();
            System.out.print("Enter client's new day of birth: ");
            int dayOfBirth = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter client's new month of birth: ");
            int monthOfBirth = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter client's new year of birth: ");
            int yearOfBirth = Integer.parseInt(scanner.nextLine());
            LocalDate birthday = LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth);
            System.out.print("Enter client's new day of registration: ");
            int dayOfRegistration = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter client's new month of registration: ");
            int monthOfRegistration = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter client's new year of registration: ");
            int yearOfRegistration = Integer.parseInt(scanner.nextLine());
            LocalDate registrationDay = LocalDate.of(yearOfRegistration, monthOfRegistration, dayOfRegistration);
            System.out.print("Enter new client points: ");
            int points = Integer.parseInt(scanner.nextLine());

            clientService.updateClient(id, name, surname, cnp, birthday, registrationDay, points);
            System.out.println("Client updated successfully!");
        } catch (ClientServiceException error) {
            System.out.println("Errors: " + error.getMessage());
        }
    }

    private void handleRemoveClient() {
        try {
            System.out.print("Enter the ID of the client to remove: ");
            String id = scanner.nextLine();

            clientService.removeClient(id);
            System.out.println("Client removed successfully!");
        } catch (ClientServiceException error) {
            System.out.println("Errors: " + error.getMessage());
        }
    }

    private void handleShowAllClients() {
        for (Client client : clientService.getAllClients())
            System.out.println(client);
    }

    private void runClientsSearch() {
        System.out.println("Dati cautarea:");
        String text = scanner.nextLine();
        System.out.println("Rezultatele cautarii sunt:");
        for (Client client : clientService.fullTextSearch(text))
            System.out.println(client);
    }

    private void runReservations() {
        while (true) {
            showMenuReservations();
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    handleAddReservation();
                    break;
                case "2":
                    handleUpdateReservation();
                    break;
                case "3":
                    handleRemoveReservation();
                    break;
                case "4":
                    handleShowAllReservations();
                    break;
                case "5":
                    runReservationsSearch();
                    break;
                case "x":
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private void handleAddReservation() {
        try {
            System.out.print("Enter reservation ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter film ID on the reservation: ");
            String idFilm = scanner.nextLine();
            System.out.print("Enter client ID on the reservation: ");
            String idCardClient = scanner.nextLine();
            System.out.print("Enter day of reservation: ");
            int day = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter month of reservation: ");
            int month = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter year of reservation: ");
            int year = Integer.parseInt(scanner.nextLine());
            LocalDate date = LocalDate.of(year, month, day);
            System.out.print("Enter time of reservation (hour): ");
            int hour = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter time of reservation (minutes): ");
            int minutes = Integer.parseInt(scanner.nextLine());
            LocalTime time = LocalTime.of(hour, minutes);

            reservationService.addReservation(id, idFilm, idCardClient, date, time);
            if (clientService.getClientRepository().getStorage().containsKey(idCardClient))
                System.out.println("Bonus points: " + clientService.getClientRepository().getStorage().get(idCardClient).getPoints());
            System.out.println("Reservation added successfully!");
        } catch (ReservationServiceException error) {
            System.out.println("Errors: " + error.getMessage());
        }
    }

    private void handleUpdateReservation() {
        try {
            System.out.print("Enter new reservation ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter new film ID on the reservation: ");
            String idFilm = scanner.nextLine();
            System.out.print("Enter new client ID on the reservation: ");
            String idCardClient = scanner.nextLine();
            System.out.print("Enter new day of reservation: ");
            int day = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter new month of reservation: ");
            int month = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter new year of reservation: ");
            int year = Integer.parseInt(scanner.nextLine());
            LocalDate date = LocalDate.of(year, month, day);
            System.out.print("Enter new time of reservation (hour): ");
            int hour = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter new time of reservation (minutes): ");
            int minutes = Integer.parseInt(scanner.nextLine());
            LocalTime time = LocalTime.of(hour, minutes);

            reservationService.updateReservation(id, idFilm, idCardClient, date, time);
            System.out.println("Reservation updated successfully!");
        } catch (ReservationServiceException error) {
            System.out.println("Errors: " + error.getMessage());
        }
    }

    private void handleRemoveReservation() {
        try {
            System.out.print("Enter the ID of the reservation to remove: ");
            String id = scanner.nextLine();

            reservationService.removeReservation(id);
            System.out.println("Reservation removed successfully!");
        } catch (ReservationServiceException error) {
            System.out.println("Errors: " + error.getMessage());
        }
    }

    private void handleShowAllReservations() {
        for (Reservation reservation : reservationService.getAllReservations())
            System.out.println(reservation);
    }

    private void runReservationsSearch() {
        System.out.println("Dati cautarea:");
        String text = scanner.nextLine();
        System.out.println("Rezultatele cautarii sunt:");
        for (Reservation reservation : reservationService.fullTextSearch(text))
            System.out.println(reservation);
    }

}
