package ch.noseryoung.backend_team7.domain.reservation;

import jakarta.persistence.*;
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
    private LocalDateTime reservationTime;

    @Column(name = "person_count")
    private int personCount;

    @OneToOne
    @JoinColumn(name = "id_table", referencedColumnName = "table_id")
    private RestaurantTable restaurantTable;
}
