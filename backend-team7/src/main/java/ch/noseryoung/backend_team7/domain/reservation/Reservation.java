package ch.noseryoung.backend_team7.domain.reservation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="reservation")
public class Reservation {
    @Id
    @Column(name="reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationId;

    @Column(name="table_no")
    private int tableNo;

    @Column(name ="table_is_available")
    private boolean tableIsAvailable;

    @Column(name = "start_time")
    private int startTime;

    @Column(name = "end_time")
    private int endTime;

    @Column(name = "person_count")
    private int personCount;

}
