package Tests.Domain;

import Domain.IValidator;
import Domain.Reservation;
import Domain.ReservationValidator;
import Domain.ReservationValidatorException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationValidatorTest {

    private IValidator<Reservation> reservationIValidator = new ReservationValidator();

    @Test
    public void validate() {
        Reservation reservation = new Reservation("1","10","20", LocalDate.of(2016,1,8), LocalTime.of(9,45));
        try {
            reservationIValidator.validate(reservation);
        } catch (ReservationValidatorException error) {
            assertTrue(true);
        }

        Reservation newReservation = new Reservation("7","7","20", LocalDate.of(2021,0,19), LocalTime.of(30,15));
        try {
            reservationIValidator.validate(newReservation);
        } catch (ReservationValidatorException error) {
            assertTrue(true);
        }
    }

}
