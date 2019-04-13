package UI;

import Service.ClientService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class ClientAddController {

    public Spinner spnId;
    public TextField txtName;
    public TextField txtSurname;
    public TextField txtCnp;
    public TextField txtDayOfBirth;
    public TextField txtMonthOfBirth;
    public TextField txtYearOfBirth;
    public TextField txtDayOfRegistration;
    public TextField txtMonthOfRegistration;
    public TextField txtYearOfRegistration;
    public TextField txtPoints;
    public Button btnAdd;
    public Button btnUpdate;
    public Button btnCancel;

    private ClientService clientService;

    public void setService(ClientService clientService) {
        this.clientService = clientService;
    }

    public void btnAddClick(ActionEvent actionEvent) {
        try {
            String id = String.valueOf(spnId.getValue());
            String name = txtName.getText();
            String surname = txtSurname.getText();
            String cnp = txtCnp.getText();

            int dayOfBirth = Integer.parseInt(txtDayOfBirth.getText());
            int monthOfBirth = Integer.parseInt(txtMonthOfBirth.getText());
            int yearOfBirth = Integer.parseInt(txtYearOfBirth.getText());
            LocalDate birthday = LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth);

            int dayOfRegistration = Integer.parseInt(txtDayOfRegistration.getText());
            int monthOfRegistration = Integer.parseInt(txtMonthOfRegistration.getText());
            int yearOfRegistration = Integer.parseInt(txtYearOfRegistration.getText());
            LocalDate registrationDay = LocalDate.of(yearOfRegistration, monthOfRegistration, dayOfRegistration);

            int points = Integer.parseInt(txtPoints.getText());

            clientService.addClient(id, name, surname, cnp, birthday, registrationDay, points);
            btnCancelClick(actionEvent);
        } catch (RuntimeException error) {
            Common.showValidationError(error.getMessage());
        }
    }

    public void btnUpdateClick(ActionEvent actionEvent) {
        try {
            String id = String.valueOf(spnId.getValue());
            String name = txtName.getText();
            String surname = txtSurname.getText();
            String cnp = txtCnp.getText();

            int dayOfBirth = Integer.parseInt(txtDayOfBirth.getText());
            int monthOfBirth = Integer.parseInt(txtMonthOfBirth.getText());
            int yearOfBirth = Integer.parseInt(txtYearOfBirth.getText());
            LocalDate birthday = LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth);

            int dayOfRegistration = Integer.parseInt(txtDayOfRegistration.getText());
            int monthOfRegistration = Integer.parseInt(txtMonthOfRegistration.getText());
            int yearOfRegistration = Integer.parseInt(txtYearOfRegistration.getText());
            LocalDate registrationDay = LocalDate.of(yearOfRegistration, monthOfRegistration, dayOfRegistration);

            int points = Integer.parseInt(txtPoints.getText());

            clientService.updateClient(id, name, surname, cnp, birthday, registrationDay, points);
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
