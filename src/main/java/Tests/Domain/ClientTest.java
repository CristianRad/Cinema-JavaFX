package Tests.Domain;

import Domain.Client;
import Domain.ClientValidator;
import Domain.IValidator;
import Repository.IRepository;
import Repository.InMemoryRepository;
import Service.ClientService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {

    private IValidator<Client> clientValidator = new ClientValidator();
    private IRepository<Client> clientRepository = new InMemoryRepository<>(clientValidator);
    private ClientService clientService = new ClientService(clientRepository);

    @Test
    public void setNameSurnameCNPBirthdayRegistrationDayPoints() {
        clientService.addClient("10","Daniela","Dan","2920417124936", LocalDate.of(1992,4,17), LocalDate.of(2010,3,21),30);

        clientService.getAllClients().get(0).setName("Teodora");
        assertEquals("Teodora", clientService.getAllClients().get(0).getName());

        clientService.getAllClients().get(0).setSurname("Prodan");
        assertEquals("Prodan", clientService.getAllClients().get(0).getSurname());

        clientService.getAllClients().get(0).setCnp("2940505124935");
        assertEquals("2940505124935", clientService.getAllClients().get(0).getCnp());

        clientService.getAllClients().get(0).setBirthday(LocalDate.of(1994,5,5));
        assertEquals("05.05.1994", clientService.getAllClients().get(0).getBirthday());

        clientService.getAllClients().get(0).setRegistrationDay(LocalDate.of(2015,10,10));
        assertEquals("10.10.2015", clientService.getAllClients().get(0).getRegistrationDay());

        clientService.getAllClients().get(0).setPoints(70);
        assertEquals(70, clientService.getAllClients().get(0).getPoints());
    }

}
