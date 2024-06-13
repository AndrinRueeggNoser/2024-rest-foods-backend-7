package ch.noseryoung.backend_team7.domain.dish;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int dishId;

    @Column(name = "dishName")
    @NotNull
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "region")
    private String region;

    @Column(name = "price")
    private Double price;
}
