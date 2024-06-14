package ch.noseryoung.backend_team7.domain.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    DishService dishService;

    // Implement Swagger! @Operation
    @GetMapping
    public List<Dish> getAllDishes() {
        return dishService.getAllDishes();
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Dish> getById(@PathVariable("Id") int id) {
        return ResponseEntity.ok().body(dishService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dish createDish(@RequestBody Dish newDish) {
        return dishService.addDish(newDish);
    }

    @PutMapping(value = "/{dishId}")
    public Dish updateDish(@PathVariable("dishId") int dishId, @RequestBody Dish dish) {
        return dishService.updateById(dishId, dish);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException nsee) {
        return ResponseEntity.status(404).body(nsee.getMessage());
    }

}
