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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tableId;

    @Column(name = "amount_of_seats")
    private int seats;

    @OneToMany(mappedBy = "restaurantTable", fetch = FetchType.LAZY)
    private Set<Reservation> reservations;
}
