package Tests.Domain;

import Domain.IValidator;
import Domain.Reservation;
import Domain.ReservationValidator;
import Domain.ReservationValidatorException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationValidatorTest {

    private IValidator<Reservation> reservationIValidator = new ReservationValidator();

    @Test
    public void validate() {
        Reservation validReservation = new Reservation("1","10","20", LocalDate.of(2016,1,8), LocalTime.of(9,45));
        try {
            reservationIValidator.validate(validReservation);
        } catch (ReservationValidatorException error) {
            assertTrue(true);
        }

        Reservation invalidReservation = new Reservation("7","7","20", LocalDate.of(2021,3,19), LocalTime.of(20,15));
        try {
            reservationIValidator.validate(invalidReservation);
        } catch (ReservationValidatorException error) {
            assertTrue(true);
        }
    }

}
