package ch.noseryoung.backend_team7.domain.table;

import ch.noseryoung.backend_team7.domain.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantTableRepository extends JpaRepository<Reservation, Integer> {
}
