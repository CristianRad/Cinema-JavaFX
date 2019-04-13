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

import java.time.LocalTime;
import java.util.List;

public class ReservationFilterController {

    public TableView tblReservations;
    public TableColumn colReservationId;
    public TableColumn colReservationFilmId;
    public TableColumn colReservationClientId;
    public TableColumn colReservationDate;
    public TableColumn colReservationTime;
    public TextField txtStartHour;
    public TextField txtStartMinutes;
    public TextField txtEndHour;
    public TextField txtEndMinutes;
    public Button btnShowResults;
    public Button btnCancel;

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

    public void btnShowResultsClick(ActionEvent actionEvent) {
        try {
            int startHour = Integer.parseInt(txtStartHour.getText());
            int startMinutes = Integer.parseInt(txtStartMinutes.getText());
            LocalTime startTime = LocalTime.of(startHour, startMinutes);

            int endHour = Integer.parseInt(txtEndHour.getText());
            int endMinutes = Integer.parseInt(txtEndMinutes.getText());
            LocalTime endTime = LocalTime.of(endHour, endMinutes);

            List<Reservation> reservationResults = reservationService.reservationsWithinTimeInterval(startTime, endTime);

            reservations.clear();
            reservations.addAll(reservationResults);
            tblReservations.setItems(reservations);
        } catch (RuntimeException error) {
            Common.showValidationError(error.getMessage());
        }
    }

    public void btnCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

}
