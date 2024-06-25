package ch.noseryoung.backend_team7.domain.reservation;

public class InvalidReservationTimeException extends Exception {
    public InvalidReservationTimeException(String message) {
        super(message);
    }
}
