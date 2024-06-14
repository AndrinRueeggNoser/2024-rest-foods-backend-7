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
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping
    public List<Dish> getAllDishes() {
        return dishService.getAllDishes();
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/{Id}")
    public ResponseEntity<Dish> getById(@PathVariable("Id") int id) {
        return ResponseEntity.ok().body(dishService.getById(id));
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dish createDish(@RequestBody Dish newDish) {
        return dishService.addDish(newDish);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping(value = "/{dishId}")
    public Dish updateDish(@PathVariable("dishId") int dishId, @RequestBody Dish dish) {
        return dishService.updateById(dishId, dish);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping("{Id}")
    public String deleteRank(@PathVariable("Id") int id) {
        dishService.deleteDish(id);
        return "Dish with id " + id + " was successfully deleted.";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException nsee) {
        return ResponseEntity.status(404).body(nsee.getMessage());
    }

}
