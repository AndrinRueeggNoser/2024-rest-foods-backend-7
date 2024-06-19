package ch.noseryoung.backend_team7.domain.table;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@jakarta.persistence.Table(name = "table")
public class Table {


    @Id
    @Column(name = "table_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tableId;

    @Column(name = "amount_of_seats")
    private int seats;

}

