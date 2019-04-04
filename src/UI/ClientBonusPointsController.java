package UI;

import Domain.Client;
import Service.ClientService;
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

public class ClientBonusPointsController {

    public TableView tblClients;
    public TableColumn colClientId;
    public TableColumn colClientName;
    public TableColumn colClientSurname;
    public TableColumn colClientCnp;
    public TableColumn colClientBirthday;
    public TableColumn colClientRegistrationDay;
    public TableColumn colClientPoints;
    public TextField txtStartDay;
    public TextField txtStartMonth;
    public TextField txtStartYear;
    public TextField txtEndDay;
    public TextField txtEndMonth;
    public TextField txtEndYear;
    public TextField txtBonusPoints;
    public Button btnApplyPoints;
    public Button btnCancel;

    public ClientService clientService;

    private ObservableList<Client> clients = FXCollections.observableArrayList();

    public void setService(ClientService clientService) {
        this.clientService = clientService;
    }

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            clients.addAll(clientService.getAllClients());
            tblClients.setItems(clients);
        });
    }

    public void btnApplyPointsClick(ActionEvent actionEvent) {
        try {
            int startDay = Integer.parseInt(txtStartDay.getText());
            int startMonth = Integer.parseInt(txtStartMonth.getText());
            int startYear = Integer.parseInt(txtStartYear.getText());
            LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);

            int endDay = Integer.parseInt(txtEndDay.getText());
            int endMonth = Integer.parseInt(txtEndMonth.getText());
            int endYear = Integer.parseInt(txtEndYear.getText());
            LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);

            int bonusPoints = Integer.parseInt(txtBonusPoints.getText());

            clientService.addBonusPoints(startDate, endDate, bonusPoints);

            clients.clear();
            clients.addAll(clientService.getAllClients());
            tblClients.setItems(clients);
        } catch (RuntimeException error) {
            Common.showValidationError(error.getMessage());
        }
    }

    public void btnCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

}
