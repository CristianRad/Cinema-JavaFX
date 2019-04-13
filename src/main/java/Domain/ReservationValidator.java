package Domain;

import java.util.Calendar;

public class ReservationValidator implements IValidator<Reservation> {

    /** Validates a reservation.
     * @param reservation is the reservation to validate.
     * @throws ReservationValidatorException if there are validation errors.
     */

    public void validate(Reservation reservation) {
        String errors = "";

        /*int id = Integer.parseInt(reservation.getId());
        int dumy = id;
        int reversedId = 0;
        while (dumy != 0) {
            reversedId = reversedId * 10 + dumy % 10;
            dumy /= 10;
        }

        if (id != reversedId)
            errors += "The ID is not palindrome!\n";*/

        if (reservation.getDate().getYear() < 1940 || reservation.getDate().getYear() > Calendar.getInstance().get(Calendar.YEAR))
            errors += "The year of reservation must be between 1940 and " + Calendar.getInstance().get(Calendar.YEAR) + "!\n";

        if (!errors.isEmpty())
            throw new ReservationValidatorException("\n" + errors);
    }

}
