package UI;

import Domain.Client;
import Domain.Film;
import Domain.Reservation;
import Service.ClientService;
import Service.FilmService;
import Service.ReservationService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController {

    public TableView tblFilms;
    public TableColumn colFilmId;
    public TableColumn colFilmTitle;
    public TableColumn colFilmYear;
    public TableColumn colFilmPrice;
    public TableColumn colFilmOnScreen;
    public TableView tblClients;
    public TableColumn colClientId;
    public TableColumn colClientName;
    public TableColumn colClientSurname;
    public TableColumn colClientCnp;
    public TableColumn colClientBirthday;
    public TableColumn colClientRegistrationDay;
    public TableColumn colClientPoints;
    public TableView tblReservations;
    public TableColumn colReservationId;
    public TableColumn colReservationFilmId;
    public TableColumn colReservationClientId;
    public TableColumn colReservationDate;
    public TableColumn colReservationTime;

    private FilmService filmService;
    private ClientService clientService;
    private ReservationService reservationService;

    private ObservableList<Film> films = FXCollections.observableArrayList();
    private ObservableList<Client> clients = FXCollections.observableArrayList();
    private ObservableList<Reservation> reservations = FXCollections.observableArrayList();

    public void setServices(FilmService filmService, ClientService clientService, ReservationService reservationService) {
        this.filmService = filmService;
        this.clientService = clientService;
        this.reservationService = reservationService;
    }

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            films.addAll(filmService.getAllFilms());
            tblFilms.setItems(films);
            clients.addAll(clientService.getAllClients());
            tblClients.setItems(clients);
            reservations.addAll(reservationService.getAllReservations());
            tblReservations.setItems(reservations);
        });
    }

    public void upsertFilmSettings(FXMLLoader fxmlLoader, String title) {
        try {
            Scene scene = new Scene(fxmlLoader.load(),235,200);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            FilmAddController controller = fxmlLoader.getController();
            controller.setService(filmService);
            stage.showAndWait();

            films.clear();
            films.addAll(filmService.getAllFilms());
        } catch (IOException error) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new window: Film Add/Update", error);
        }
    }

    public void btnAddFilmClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("filmAdd.fxml"));
        upsertFilmSettings(fxmlLoader, "Film Add");
    }

    public void btnUpdateFilmClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("filmUpdate.fxml"));
        upsertFilmSettings(fxmlLoader, "Film Update");
    }

    public void btnRemoveFilmClick(ActionEvent actionEvent) {
        Film selected = (Film) tblFilms.getSelectionModel().getSelectedItem();
        if (selected != null)
        try {
            filmService.removeFilm(selected.getId());
            films.clear();
            films.addAll(filmService.getAllFilms());
        } catch (MainControllerException error) {
            Common.showValidationError(error.getMessage());
        }
    }

    public void upsertClientSettings(FXMLLoader fxmlLoader, String title) {
        try {
            Scene scene = new Scene(fxmlLoader.load(),250,280);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            ClientAddController controller = fxmlLoader.getController();
            controller.setService(clientService);
            stage.showAndWait();

            clients.clear();
            clients.addAll(clientService.getAllClients());
        } catch (IOException error) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE,"Failed to create new window: Client Add/Update", error);
        }
    }

    public void btnAddClientClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("clientAdd.fxml"));
        upsertClientSettings(fxmlLoader, "Client Add");
    }

    public void btnUpdateClientClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("clientUpdate.fxml"));
        upsertClientSettings(fxmlLoader, "Client Update");
    }

    public void btnRemoveClientClick(ActionEvent actionEvent) {
        Client selected = (Client) tblClients.getSelectionModel().getSelectedItem();
        if (selected != null)
            try {
                clientService.removeClient(selected.getId());
                clients.clear();
                clients.addAll(clientService.getAllClients());
            } catch (MainControllerException error) {
                Common.showValidationError(error.getMessage());
            }
    }

    public void upsertReservationSettings(FXMLLoader fxmlLoader, String title) {
        try {
            Scene scene = new Scene(fxmlLoader.load(),270,250);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            ReservationAddController controller = fxmlLoader.getController();
            controller.setService(reservationService);
            stage.showAndWait();

            reservations.clear();
            reservations.addAll(reservationService.getAllReservations());
        } catch (IOException error) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new window: Reservation Add/Update", error);
        }
    }

    public void btnAddReservationClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("reservationAdd.fxml"));
        upsertReservationSettings(fxmlLoader,"Reservation Add");
    }

    public void btnUpdateReservationClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("reservationUpdate.fxml"));
        upsertReservationSettings(fxmlLoader,"Reservation Update");
    }

    public void btnRemoveReservationClick(ActionEvent actionEvent) {
        Reservation selected = (Reservation) tblReservations.getSelectionModel().getSelectedItem();
        if (selected != null)
            try {
                reservationService.removeReservation(selected.getId());
                reservations.clear();
                reservations.addAll(reservationService.getAllReservations());
            } catch (MainControllerException error) {
                Common.showValidationError(error.getMessage());
            }
    }

    public void btnTextSearchClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Search.fxml"));

            Scene scene = new Scene(fxmlLoader.load(),1400,900);
            Stage stage = new Stage();
            stage.setTitle("Full Text Search");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            SearchController controller = fxmlLoader.getController();
            controller.setService(filmService, clientService, reservationService);
            stage.showAndWait();

        } catch (IOException error) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new window: Full Text Search", error);
        }
    }

    public void btnReservationFilterClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ReservationFilter.fxml"));

            Scene scene = new Scene(fxmlLoader.load(),600,600);
            Stage stage = new Stage();
            stage.setTitle("Reservation Filtering");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            ReservationFilterController controller = fxmlLoader.getController();
            controller.setService(reservationService);
            stage.showAndWait();

        } catch (IOException error) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new window: Reservation Filtering", error);
        }
    }

    public void btnFilmSortClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("FilmSort.fxml"));

            Scene scene = new Scene(fxmlLoader.load(),600,600);
            Stage stage = new Stage();
            stage.setTitle("Film Sort by Reservations");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            FilmSortController controller = fxmlLoader.getController();
            controller.setService(filmService);
            stage.showAndWait();

        } catch (IOException error) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new window: Film Sort by Reservations", error);
        }
    }

    public void btnClientSortClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ClientSort.fxml"));

            Scene scene = new Scene(fxmlLoader.load(),700,600);
            Stage stage = new Stage();
            stage.setTitle("Client Sort by Card Points");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            ClientSortController controller = fxmlLoader.getController();
            controller.setService(clientService);
            stage.showAndWait();

        } catch (IOException error) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new window: Client Sort by Card Points", error);
        }
    }

    public void btnReservationRemoveFilterClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ReservationRemoveFilter.fxml"));

            Scene scene = new Scene(fxmlLoader.load(),600,600);
            Stage stage = new Stage();
            stage.setTitle("Reservation Remove Filter");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            ReservationRemoveFilterController controller = fxmlLoader.getController();
            controller.setService(reservationService);
            stage.showAndWait();

        } catch (IOException error) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new window: Reservation Remove Filter", error);
        }
    }

    public void btnClientBonusPoints(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ClientBonusPoints.fxml"));

            Scene scene = new Scene(fxmlLoader.load(),700,700);
            Stage stage = new Stage();
            stage.setTitle("Client Bonus Points");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            ClientBonusPointsController controller = fxmlLoader.getController();
            controller.setService(clientService);
            stage.showAndWait();

        } catch (IOException error) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new window: Client Bonus Points", error);
        }
    }

}
