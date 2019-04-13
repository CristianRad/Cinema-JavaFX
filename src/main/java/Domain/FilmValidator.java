package Domain;

import java.util.Calendar;

import static Domain.PalindromeValidator.isPalindrome;

public class FilmValidator implements IValidator<Film> {

    /** Validates a film.
     * @param film the reservation to validate.
     * @throws FilmValidatorException if there are validation errors.
     */

    public void validate (Film film) {
        String errors = "";

        if (isPalindrome(film.getId()))
            errors += String.format("The film's ID (%s) is a palindrome number!\n", film.getId());

        if (film.getTicketPrice() <= 0)
            errors += "Ticket price must be greater than 0!\n";

        if (film.getYear() < 1900 || film.getYear() > Calendar.getInstance().get(Calendar.YEAR))
            errors += "The year is not valid!\n";

        if (!errors.isEmpty())
            throw new FilmValidatorException("\n" + errors);
    }

}
