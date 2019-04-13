package Tests.Service;

import Domain.Client;
import Domain.ClientValidator;
import Domain.IValidator;
import Repository.IRepository;
import Repository.InMemoryRepository;
import Service.ClientService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientServiceTest {

    private IValidator<Client> clientValidator = new ClientValidator();
    private IRepository<Client> clientRepository = new InMemoryRepository<>(clientValidator);
    private ClientService clientService = new ClientService(clientRepository);

    @Test
    public void add() {
        clientService.addClient("1","Robert","Bura","1910909126212", LocalDate.of(1991,9,9), LocalDate.of(2013,10,14),43);
        assertEquals(1, clientService.getAllClients().size());
    }

    @Test
    public void update() {
        clientService.addClient("1","Robert","Bura","1910909126212", LocalDate.of(1991,9,9), LocalDate.of(2013,10,14),43);
        clientService.updateClient("1","Robert","Bura","1910909126212", LocalDate.of(1991,9,9), LocalDate.of(2018,4,10),43);

        assertEquals(LocalDate.of(2018,4,10), clientService.getAllClients().get(0).getRegistrationDay());
    }

    @Test
    public void remove() {
        clientService.addClient("1","Robert","Bura","1910909126212", LocalDate.of(1991,9,9), LocalDate.of(2013,10,14),43);
        clientService.removeClient("1");

        assertEquals(0, clientService.getAllClients().size());
    }

    @Test
    public void getAll() {
        clientService.addClient("1","Robert","Bura","1910909126212", LocalDate.of(1991,9,9), LocalDate.of(2013,10,14),43);
        assertEquals(1, clientService.getAllClients().size());
    }

}
