package Tests.Domain;

import Domain.Client;
import Domain.ClientValidator;
import Domain.ClientValidatorException;
import Domain.IValidator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientValidatorTest {

    private IValidator<Client> clientIValidator = new ClientValidator();

    @Test
    public void validate() {
        Client client = new Client("17","Bogdan","Hadarean","1901210124936", LocalDate.of(1990,12,10), LocalDate.of(2014,12,31),-45);
        assertThrows(ClientValidatorException.class, () -> clientIValidator.validate(client));
    }

}
