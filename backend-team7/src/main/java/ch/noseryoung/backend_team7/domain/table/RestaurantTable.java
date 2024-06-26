package ch.noseryoung.backend_team7.domain.table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ch.noseryoung.backend_team7.domain.reservation.Reservation;

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

    @OneToOne(mappedBy = "restaurantTable", cascade = CascadeType.ALL, optional = true)
    @JsonManagedReference
    private Reservation reservation;
}
