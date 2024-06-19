package ch.noseryoung.backend_team7.domain.reservation;

import ch.noseryoung.backend_team7.domain.dish.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.List;

@RestController
@RequestMapping
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservations")
    public List<Reservation> getAll() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<Reservation> getById(@PathVariable("reservationId") int reservationId) throws InstanceNotFoundException {
        return ResponseEntity.ok().body(reservationService.getByID(reservationId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation createReservation(@RequestBody Reservation newReservation) {
        return reservationService.addReservation(newReservation);
    }

    @PutMapping(value = "/reservation/{reservationId}")
    public Reservation updateReservation(@PathVariable("reservationId") int reservationId, @RequestBody Reservation reservation) throws InstanceNotFoundException, InstanceAlreadyExistsException {
        return reservationService.updateReservation(reservation, reservationId);
    }

    @DeleteMapping("reservation/{reservationId}")
    public String deleteRank(@PathVariable("reservationId") int reservationId) throws InstanceNotFoundException {
        reservationService.deleteReservation(reservationId);
        return "Reservation with id " + reservationId + " was successfully deleted.";
    }
}
