package UI;

import Service.FilmReservationVM;
import Service.FilmService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class FilmSortController {
    
    public TableView tblFilms;
    public TableColumn colFilmId;
    public TableColumn colFilmTitle;
    public TableColumn colFilmYear;
    public TableColumn colFilmPrice;
    public TableColumn colFilmOnScreen;
    public TableColumn colFilmReservations;

    private FilmService filmService;

    private ObservableList<FilmReservationVM> films = FXCollections.observableArrayList();

    public void setService(FilmService filmService) {
        this.filmService = filmService;
    }

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            try {
                List<FilmReservationVM> sortedFilms = filmService.getOrderedByReservations();

                films.addAll(sortedFilms);
                tblFilms.setItems(films);
            } catch (RuntimeException error) {
                Common.showValidationError(error.getMessage());
            }
        });
    }

}
