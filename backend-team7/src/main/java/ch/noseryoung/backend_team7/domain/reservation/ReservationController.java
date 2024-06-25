package ch.noseryoung.backend_team7.domain.reservation;

import ch.noseryoung.backend_team7.domain.dish.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    /**
     * Gets all reservations
     * @return A list of all reservations
     */
    @PreAuthorize("hasAuthority('GET')")
    @GetMapping
    public List<Reservation> getAll() {
        return reservationService.getAllReservations();
    }

    /**
     * Gets a single reservation by its id
     * @param reservationId The id of the reservation to get
     * @return Status code 200
     * @throws InstanceNotFoundException if the reservation with the specified id is not found
     */
    @PreAuthorize("hasAuthority('GET')")
    @GetMapping("/{reservationId}")
    public ResponseEntity<Reservation> getById(@PathVariable("reservationId") int reservationId) throws InstanceNotFoundException {
        return ResponseEntity.ok().body(reservationService.getByID(reservationId));
    }

    /**
     * Creates a new reservation
     * @param newReservation The reservation object to create
     * @return Status code 201
     */
    @PreAuthorize("hasAuthority('POST')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation newReservation) {
        return ResponseEntity.status(201).body(reservationService.addReservation(newReservation));
    }

    /**
     * Updates an existing reservation
     * @param reservationId The id of the reservation to update
     * @param reservation Updated reservation data
     * @return Status code 200
     * @throws InstanceNotFoundException if the reservation with the specified id is not found
     * @throws InstanceAlreadyExistsException if a reservation like this already exists
     */
    @PreAuthorize("hasAuthority('PUT')")
    @PutMapping(value = "/{reservationId}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable("reservationId") int reservationId, @RequestBody Reservation reservation) throws InstanceNotFoundException, InstanceAlreadyExistsException {
        return ResponseEntity.status(200).body(reservationService.updateReservation(reservation, reservationId));
    }

    /**
     * Deletes a reservation by its id
     * @param reservationId Used to delete a certain reservation
     * @return "Success" message for deleting a reservation
     * @throws InstanceNotFoundException if the reservation with the specified id is not found
     */
    @PreAuthorize("hasAuthority('DELETE')")
    @DeleteMapping("/{reservationId}")
    public String deleteRank(@PathVariable("reservationId") int reservationId) throws InstanceNotFoundException {
        reservationService.deleteReservation(reservationId);
        return "Reservation with id " + reservationId + " was successfully deleted.";
    }

    /**
     * Handles InstanceNotFoundException
     * @param infe The exception to handle
     * @return Status code 404 and error message
     */
    @ExceptionHandler(InstanceNotFoundException.class)
    public ResponseEntity<String> instanceNotFoundException(InstanceNotFoundException infe) {
        return ResponseEntity.status(404).body(infe.getMessage());
    }

    /**
     * Handles InstanceAlreadyExistsException
     * @param iaee The exception to handle
     * @return Status code 404 and error message
     */
    @ExceptionHandler(InstanceAlreadyExistsException.class)
    public ResponseEntity<String> instanceAlreadyExistException(InstanceAlreadyExistsException iaee) {
        return ResponseEntity.status(404).body(iaee.getMessage());
    }
}
