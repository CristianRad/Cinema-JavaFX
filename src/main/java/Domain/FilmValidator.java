package Domain;

import java.util.Calendar;

public class FilmValidator implements IValidator<Film> {

    /** Validates a film.
     * @param film the reservation to validate.
     * @throws FilmValidatorException if there are validation errors.
     */

    public void validate (Film film) {
        String errors = "";

        int id = Integer.parseInt(film.getId());
        int dumy = id;
        int reversedId = 0;
        while (dumy != 0) {
            reversedId = reversedId * 10 + dumy % 10;
            dumy /= 10;
        }

        if (id != reversedId)
            errors += "The ID is not palindrome!\n";

        if (film.getTicketPrice() <= 0)
            errors += "Ticket price must be greater than 0!\n";

        if (film.getYear() < 1900 || film.getYear() > Calendar.getInstance().get(Calendar.YEAR))
            errors += "The year is not valid!\n";

        if (!errors.isEmpty())
            throw new FilmValidatorException("\n" + errors);
    }

}
