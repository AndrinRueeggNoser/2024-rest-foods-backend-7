package ch.noseryoung.backend_team7.domain.reservation;

import ch.noseryoung.backend_team7.domain.dish.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.List;
/**
 * TODO:
 * Once exceptions are added in ReservationService, they should also be added into
 * ReservationController. Exception-handlers have already been implemented.
 */

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<Reservation> getAll() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<Reservation> getById(@PathVariable("reservationId") int reservationId) throws InstanceNotFoundException {
        return ResponseEntity.ok().body(reservationService.getByID(reservationId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation newReservation) {
        return ResponseEntity.status(201).body(reservationService.addReservation(newReservation));
    }

    @PutMapping(value = "/{reservationId}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable("reservationId") int reservationId, @RequestBody Reservation reservation) throws InstanceNotFoundException, InstanceAlreadyExistsException {
        return ResponseEntity.status(200).body(reservationService.updateReservation(reservation, reservationId));
    }

    @DeleteMapping("/{reservationId}")
    public String deleteRank(@PathVariable("reservationId") int reservationId) throws InstanceNotFoundException {
        reservationService.deleteReservation(reservationId);
        return "Reservation with id " + reservationId + " was successfully deleted.";
    }

    @ExceptionHandler(InstanceNotFoundException.class)
    public ResponseEntity<String> instanceNotFoundException(InstanceNotFoundException infe) {
        return ResponseEntity.status(404).body(infe.getMessage());
    }

    @ExceptionHandler(InstanceAlreadyExistsException.class)
    public ResponseEntity<String> instanceAlreadyExistException(InstanceAlreadyExistsException iaee) {
        return ResponseEntity.status(404).body(iaee.getMessage());
    }
}
