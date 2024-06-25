package ch.noseryoung.backend_team7.domain.reservation;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ch.noseryoung.backend_team7.domain.table.RestaurantTable;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationId;

    @Column(name = "reservation_time")
    @NotNull(message = "Reservation time cannot be empty")
    @Future(message = "Reservation time must be in the future")
    private LocalDateTime reservationTime;

    @Column(name = "person_count")
    @Min(value = 1, message = "Person count must be at least 1")
    private int personCount;

    @OneToOne
    @JoinColumn(name = "id_table", referencedColumnName = "table_id")

    @NotNull(message = "Restaurant table cannot be empty")
    private RestaurantTable restaurantTable;
}
