package ch.noseryoung.backend_team7.domain.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
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

    @GetMapping("/{dishId}")
    public ResponseEntity<Dish> getById(@PathVariable("dishId") int dishId) throws InstanceNotFoundException {
        return ResponseEntity.ok().body(dishService.getById(dishId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dish createDish(@RequestBody Dish newDish) throws InstanceAlreadyExistsException {
        return dishService.addDish(newDish);
    }

    @PutMapping(value = "/{dishId}")
    public Dish updateDish(@PathVariable("dishId") int dishId, @RequestBody Dish dish) throws InstanceNotFoundException {
        return dishService.updateById(dishId, dish);
    }

    @DeleteMapping("{dishId}")
    public String deleteRank(@PathVariable("dishId") int dishId) throws InstanceNotFoundException {
        dishService.deleteDish(dishId);
        return "Dish with id " + dishId + " was successfully deleted.";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException nsee) {
        return ResponseEntity.status(404).body(nsee.getMessage());
    }

}
