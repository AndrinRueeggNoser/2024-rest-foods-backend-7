package ch.noseryoung.backend_team7.domain.reservation;

public class TableAlreadyReservedException extends Exception {
    public TableAlreadyReservedException(String message) {
        super(message);
    }
}
