package Domain;

import java.util.Calendar;

import static Domain.PalindromeValidator.isPalindrome;

public class ReservationValidator implements IValidator<Reservation> {

    /** Validates a reservation.
     * @param reservation is the reservation to validate.
     * @throws ReservationValidatorException if there are validation errors.
     */

    public void validate(Reservation reservation) {
        String errors = "";

        if (isPalindrome(reservation.getId()))
            errors += String.format("The reservation's ID (%s) is a palindrome number!\n", reservation.getId());

        if (reservation.getDate().getYear() < 1940 || reservation.getDate().getYear() > Calendar.getInstance().get(Calendar.YEAR))
            errors += "The year of reservation must be between 1940 and " + Calendar.getInstance().get(Calendar.YEAR) + "!\n";

        if (!errors.isEmpty())
            throw new ReservationValidatorException("\n" + errors);
    }

}
