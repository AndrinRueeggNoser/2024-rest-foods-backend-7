package ch.noseryoung.backend_team7.domain.table;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ch.noseryoung.backend_team7.domain.reservation.Reservation;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "restaurant_table")
public class RestaurantTable {

    @Id
    @Column(name = "table_id")
    private int tableId;

    @Column(name = "amount_of_seats")
    private int seats;

    @Column(name = "is_available")
    private boolean isAvailable;

    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;


}
