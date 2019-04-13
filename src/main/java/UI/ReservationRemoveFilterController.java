package UI;

import Domain.Reservation;
import Service.ReservationService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class ReservationRemoveFilterController {

    public TableView tblReservations;
    public TableColumn colReservationId;
    public TableColumn colReservationFilmId;
    public TableColumn colReservationClientId;
    public TableColumn colReservationDate;
    public TableColumn colReservationTime;
    public TextField txtStartDay;
    public TextField txtStartMonth;
    public TextField txtStartYear;
    public TextField txtEndDay;
    public TextField txtEndMonth;
    public TextField txtEndYear;
    public Button btnRemove;
    public Button btnCancel;
    public Button btnUndo;
    public Button btnRedo;

    public ReservationService reservationService;

    private ObservableList<Reservation> reservations = FXCollections.observableArrayList();

    public void setService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            reservations.addAll(reservationService.getAllReservations());
            tblReservations.setItems(reservations);
        });
    }

    public void btnRemoveClick(ActionEvent actionEvent) {
        try {
            int startDay = Integer.parseInt(txtStartDay.getText());
            int startMonth = Integer.parseInt(txtStartMonth.getText());
            int startYear = Integer.parseInt(txtStartYear.getText());
            LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);

            int endDay = Integer.parseInt(txtEndDay.getText());
            int endMonth = Integer.parseInt(txtEndMonth.getText());
            int endYear = Integer.parseInt(txtEndYear.getText());
            LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);

            reservationService.removeReservationsWithinDateInterval(startDate, endDate);

            reservations.clear();
            reservations.addAll(reservationService.getAllReservations());
            tblReservations.setItems(reservations);
        } catch (RuntimeException error) {
            Common.showValidationError(error.getMessage());
        }
    }

    public void btnCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void btnUndoRemoveReservationsClick(ActionEvent actionEvent) {
        reservationService.undo();
        reservations.clear();
        reservations.addAll(reservationService.getAllReservations());
    }

    public void btnRedoRemoveReservationsClick(ActionEvent actionEvent) {
        reservationService.redo();
        reservations.clear();
        reservations.addAll(reservationService.getAllReservations());
    }

}
