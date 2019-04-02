package UI;

import Domain.Client;
import Domain.Film;
import Domain.Reservation;
import Service.ClientService;
import Service.FilmService;
import Service.ReservationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SearchController {

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
    public Button btnSearch;
    public Button btnCancel;
    public TextField txtSearchText;

    public FilmService filmService;
    public ClientService clientService;
    public ReservationService reservationService;

    private ObservableList<Film> films = FXCollections.observableArrayList();
    private ObservableList<Client> clients = FXCollections.observableArrayList();
    private ObservableList<Reservation> reservations = FXCollections.observableArrayList();

    public void setService(FilmService filmService, ClientService clientService, ReservationService reservationService) {
        this.filmService = filmService;
        this.clientService = clientService;
        this.reservationService = reservationService;
    }

    public void btnSearchClick(ActionEvent actionEvent) {
        String text = txtSearchText.getText();
    }

    public void btnCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

}
