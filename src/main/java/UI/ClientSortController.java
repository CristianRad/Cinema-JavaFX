package UI;

import Domain.Client;
import Service.ClientService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.Comparator;
import java.util.List;

public class ClientSortController {

    public TableView tblClients;
    public TableColumn colClientId;
    public TableColumn colClientName;
    public TableColumn colClientSurname;
    public TableColumn colClientCnp;
    public TableColumn colClientBirthday;
    public TableColumn colClientRegistrationDay;
    public TableColumn colClientPoints;

    public ClientService clientService;

    private ObservableList<Client> clients = FXCollections.observableArrayList();

    public void setService(ClientService clientService) {
        this.clientService = clientService;
    }

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            try {
                List<Client> sortedClients = clientService.getAllClients();
                sortedClients.sort(Comparator.comparing(Client::getPoints).reversed());

                clients.addAll(sortedClients);
                tblClients.setItems(clients);
            } catch (RuntimeException error) {
                Common.showValidationError(error.getMessage());
            }
        });
    }

}
