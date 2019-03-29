package UI;

import Domain.Film;
import Service.ClientService;
import Service.FilmService;
import Service.ReservationService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainController {

    public TableView tblFilms;
    public TableColumn colFilmId;
    public TableColumn colFilmTitle;
    public TableColumn colFilmYear;
    public TableColumn colFilmPrice;
    public TableColumn colFilmOnScreen;
    public TextField txtFilmId;
    public TextField txtFilmTitle;
    public TextField txtFilmYear;
    public TextField txtFilmPrice;
    public CheckBox chkOnScreen;
    public Button btnAddFilm;
    public Button btnUpdateFilm;
    public Button btnRemoveFilm;

    private FilmService filmService;
    private ClientService clientService;
    private ReservationService reservationService;

    private ObservableList<Film> films = FXCollections.observableArrayList();

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
        });
    }

    public void btnAddFilmClick(ActionEvent actionEvent) {
        try {
            String id = txtFilmId.getText();
            String title = txtFilmTitle.getText();
            int year = Integer.parseInt(txtFilmYear.getText());
            double price = Double.parseDouble(txtFilmPrice.getText());
            boolean onScreen = chkOnScreen.isSelected();

            filmService.addFilm(id, title, year, price, onScreen);
            films.clear();
            films.addAll(filmService.getAllFilms());
        } catch (MainControllerException error) {
            Common.showValidationError(error.getMessage());
        }
    }

    public void btnUpdateFilmClick(ActionEvent actionEvent) {
        try {
            String id = txtFilmId.getText();
            String title = txtFilmTitle.getText();
            int year = Integer.parseInt(txtFilmYear.getText());
            double price = Double.parseDouble(txtFilmPrice.getText());
            boolean onScreen = chkOnScreen.isSelected();

            filmService.updateFilm(id, title, year, price, onScreen);
            films.clear();
            films.addAll(filmService.getAllFilms());
        } catch (MainControllerException error) {
            Common.showValidationError(error.getMessage());
        }
    }

    public void btnRemoveFilmClick(ActionEvent actionEvent) {
        try {
            String id = txtFilmId.getText();

            filmService.removeFilm(id);
            films.clear();
            films.addAll(filmService.getAllFilms());
        } catch (MainControllerException error) {
            Common.showValidationError(error.getMessage());
        }
    }

}
