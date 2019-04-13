package Domain;

import java.util.Calendar;

public class ClientValidator implements IValidator<Client> {

    /** Validates a reservation.
     * @param client the client to validate.
     * @throws ClientValidatorException if there are validation errors.
     */

    public void validate(Client client) {
        String errors = "";

        if (client.getCnp().length() != 13)
            throw new ClientValidatorException("The CNP must have exactly 13 digits!\n");

        if (client.getBirthday().getYear() < 1920 || client.getBirthday().getYear() > Calendar.getInstance().get(Calendar.YEAR))
            errors += "The year of birth must be between 1920 and " + Calendar.getInstance().get(Calendar.YEAR) + "!\n";

        if (client.getRegistrationDay().getYear() < 1940 || client.getRegistrationDay().getYear() > Calendar.getInstance().get(Calendar.YEAR))
            errors += "The year of registration must be between 1940 and " + Calendar.getInstance().get(Calendar.YEAR) + "!\n";

        if (client.getPoints() < 0)
            errors += "Bonus points cannot be a negative value!\n";

        if (!errors.isEmpty())
            throw new ClientValidatorException("\n" + errors);
    }

}
