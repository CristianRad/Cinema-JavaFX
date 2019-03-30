package UI;

import Domain.Client;
import Domain.Film;
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

    private FilmService filmService;
    private ClientService clientService;
    private ReservationService reservationService;

    private ObservableList<Film> films = FXCollections.observableArrayList();
    private ObservableList<Client> clients = FXCollections.observableArrayList();

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

}
