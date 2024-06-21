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

    /**
     * Gets a single dish by its id
     * @param dishId Get object to get a dish by id
     * @return Status code 200
     * @throws InstanceNotFoundException
     */
    @GetMapping("/{dishId}")
    public ResponseEntity<Dish> getById(@PathVariable("dishId") int dishId) throws InstanceNotFoundException {
        return ResponseEntity.ok().body(dishService.getById(dishId));
    }

    @PostMapping
    public ResponseEntity<Dish> createDish(@RequestBody Dish newDish) throws InstanceAlreadyExistsException {
        return ResponseEntity.status(201).body(dishService.addDish(newDish));
    }

    @PutMapping(value = "/{dishId}")
    public ResponseEntity<Dish> updateDish(@PathVariable("dishId") int dishId, @RequestBody Dish dish) throws InstanceNotFoundException {
        return ResponseEntity.status(200).body(dishService.updateById(dishId, dish));
    }

    @DeleteMapping("{dishId}")
    public String deleteDish(@PathVariable("dishId") int dishId) throws InstanceNotFoundException {
        dishService.deleteDish(dishId);
        return "Dish with id " + dishId + " was successfully deleted.";
    }

    @ExceptionHandler(InstanceNotFoundException.class)
    public ResponseEntity<String> instanceNotFoundException(InstanceNotFoundException infe) {
        return ResponseEntity.status(404).body(infe.getMessage());
    }

    @ExceptionHandler(InstanceAlreadyExistsException.class)
    public ResponseEntity<String> instanceAlreadyExistException(InstanceAlreadyExistsException iaee) {
        return ResponseEntity.status(404).body(iaee.getMessage());
    }
}
