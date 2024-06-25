package ch.noseryoung.backend_team7.domain.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getByID(int reservationId) throws InstanceNotFoundException {
        return reservationRepository.findById(reservationId).orElseThrow(() -> new InstanceNotFoundException("Reservation with id " + reservationId + " could not be found."));
    }

    /**
     * When selected table is already reserved, exception should be thrown.
     * If reservation time is too early or too late, exception should be thrown.
     **/
    public Reservation addReservation(Reservation newReservation) {
        return reservationRepository.save(newReservation);
    }

    public Reservation updateReservation(Reservation reservation, int reservationId) throws InstanceNotFoundException {
        if (!reservationRepository.existsById(reservationId)) {
            throw new InstanceNotFoundException("Reservation with id " + reservationId + " could not be found.");
        }
        /**
         * IF UPDATED RESERVATION:
         * When selected table is already reserved, exception should be thrown.
         * If reservation time is too early or too late, exception should be thrown.
         **/
        reservation.setReservationId(reservationId);
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(int reservationId) throws InstanceNotFoundException {
        if (!reservationRepository.existsById(reservationId)) {
            throw new InstanceNotFoundException("Reservation with id " + reservationId + " could not be found.");
        }
        reservationRepository.deleteById(reservationId);
    }
}
