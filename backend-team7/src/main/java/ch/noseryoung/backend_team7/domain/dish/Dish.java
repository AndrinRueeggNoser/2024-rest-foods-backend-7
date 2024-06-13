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
    @Column(name = "dish_id")
    private int dishId;

    @Column(name = "dish_name")
    private String dishName;

    @Column(name = "description")
    private String description;

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "is_chefs_choice")
    private boolean isChefsChoice;

    @Column(name = "region")
    private String region;

    @Column(name = "price")
    private Double price;

    public Dish(int dishId, String dishName, String description, String ingredients, String region, Double price) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.description = description;
        this.ingredients = ingredients;
        this.region = region;
        this.price = price;
    }
}
