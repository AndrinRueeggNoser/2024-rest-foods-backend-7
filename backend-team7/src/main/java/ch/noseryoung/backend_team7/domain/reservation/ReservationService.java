package ch.noseryoung.backend_team7.domain.reservation;

import ch.noseryoung.backend_team7.domain.table.RestaurantTable;
import ch.noseryoung.backend_team7.domain.table.RestaurantTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RestaurantTableRepository restaurantTableRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getByID(int reservationId) throws InstanceNotFoundException {
        return reservationRepository.findById(reservationId).orElseThrow(() -> new InstanceNotFoundException("Reservation with id " + reservationId + " could not be found."));
    }

    public Reservation addReservation(Reservation newReservation) throws TableAlreadyReservedException, InvalidReservationTimeException, InstanceNotFoundException {
        validateReservation(newReservation);
        return reservationRepository.save(newReservation);
    }

    public Reservation updateReservation(Reservation reservation, int reservationId) throws TableAlreadyReservedException, InvalidReservationTimeException, InstanceNotFoundException {

        if (!reservationRepository.existsById(reservationId)) {
            throw new InstanceNotFoundException("Reservation with id " + reservationId + " could not be found.");
        }
        validateReservation(reservation);
        reservation.setReservationId(reservationId);
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(int reservationId) throws InstanceNotFoundException {
        if (!reservationRepository.existsById(reservationId)) {
            throw new InstanceNotFoundException("Reservation with id " + reservationId + " could not be found.");
        }
        reservationRepository.deleteById(reservationId);
    }


    private void validateReservation(Reservation reservation) throws TableAlreadyReservedException, InvalidReservationTimeException, InstanceNotFoundException {
        if (reservation.getRestaurantTable() != null) {
            int tableId = reservation.getRestaurantTable().getTableId();
            RestaurantTable table = restaurantTableRepository.findById(tableId).orElseThrow(() -> new InstanceNotFoundException("Table with id " + tableId + " could not be found."));

            if (!table.isAvailable()) {
                throw new TableAlreadyReservedException("Table with id " + tableId + " is already reserved.");
            }
        }

        LocalTime reservationTime = reservation.getReservationTime().toLocalTime();
        if (reservationTime.isBefore(LocalTime.of(12, 0)) || reservationTime.isAfter(LocalTime.of(23, 0))) {
            throw new InvalidReservationTimeException("Reservation time " + reservationTime + " is outside of the allowed hours (12:00 - 23:00).");
        }
    }
}
