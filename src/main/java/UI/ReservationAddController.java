package UI;

import Service.ReservationService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationAddController {

    public Spinner spnId;
    public TextField txtFilmId;
    public TextField txtClientId;
    public TextField txtDayOfReservation;
    public TextField txtMonthOfReservation;
    public TextField txtYearOfReservation;
    public TextField txtHour;
    public TextField txtMinutes;
    public Button btnAdd;
    public Button btnUpdate;
    public Button btnCancel;

    private ReservationService reservationService;

    public void setService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public void btnAddClick(ActionEvent actionEvent) {
        try {
            String id = String.valueOf(spnId.getValue());
            String idFilm = txtFilmId.getText();
            String idCardClient = txtClientId.getText();

            int dayOfReservation = Integer.parseInt(txtDayOfReservation.getText());
            int monthOfReservation = Integer.parseInt(txtMonthOfReservation.getText());
            int yearOfReservation = Integer.parseInt(txtYearOfReservation.getText());
            LocalDate dateOfReservation = LocalDate.of(yearOfReservation, monthOfReservation, dayOfReservation);

            int hour = Integer.parseInt(txtHour.getText());
            int minutes = Integer.parseInt(txtMinutes.getText());
            LocalTime timeOfReservation = LocalTime.of(hour, minutes);

            reservationService.addReservation(id, idFilm, idCardClient, dateOfReservation, timeOfReservation);
            btnCancelClick(actionEvent);
        } catch (RuntimeException error) {
            Common.showValidationError(error.getMessage());
        }
    }

    public void btnUpdateClick(ActionEvent actionEvent) {
        try {
            String id = String.valueOf(spnId.getValue());
            String idFilm = txtFilmId.getText();
            String idCardClient = txtClientId.getText();

            int dayOfReservation = Integer.parseInt(txtDayOfReservation.getText());
            int monthOfReservation = Integer.parseInt(txtMonthOfReservation.getText());
            int yearOfReservation = Integer.parseInt(txtYearOfReservation.getText());
            LocalDate dateOfReservation = LocalDate.of(yearOfReservation, monthOfReservation, dayOfReservation);

            int hour = Integer.parseInt(txtHour.getText());
            int minutes = Integer.parseInt(txtMinutes.getText());
            LocalTime timeOfReservation = LocalTime.of(hour, minutes);

            reservationService.updateReservation(id, idFilm, idCardClient, dateOfReservation, timeOfReservation);
            btnCancelClick(actionEvent);
        } catch (RuntimeException error) {
            Common.showValidationError(error.getMessage());
        }
    }

    public void btnCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

}
