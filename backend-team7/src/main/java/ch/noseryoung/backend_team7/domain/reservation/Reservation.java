package ch.noseryoung.backend_team7.domain.reservation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ch.noseryoung.backend_team7.domain.table.RestaurantTable;

@Setter
@Getter
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationId;

    @OneToOne
    @JoinColumn(name = "id_table", referencedColumnName = "table_id")
    private RestaurantTable restaurantTable;

    @Column(name = "start_time")
    private int startTime;

    @Column(name = "end_time")
    private int endTime;

    @Column(name = "person_count")
    private int personCount;


}
