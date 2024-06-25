package ch.noseryoung.backend_team7.domain.dish;

import io.swagger.v3.oas.annotations.Operation;
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

    /**
     * Gets all dishes
     *
     * @return A list of all dishes
     */

    @GetMapping
    @Operation(summary = "Get all dishes", description = "Displays a list of all dishes along with their id, name, description, image, region, price, chefs choice and returns a JSON with the status code 200.")
    public List<Dish> getAllDishes() {
        return dishService.getAllDishes();
    }

    /**
     * Gets a single dish by its id
     *
     * @param dishId The id of the dish to get
     * @return Status code 200
     * @throws InstanceNotFoundException if the dish with the specified id is not found
     */
    @GetMapping("/{dishId}")
    @Operation(summary = "Get dish by ID", description = "Retrieves a dishes information by its id and returns a JSON object with the status code 200.")
    public ResponseEntity<Dish> getById(@PathVariable("dishId") int dishId) throws InstanceNotFoundException {
        return ResponseEntity.ok().body(dishService.getById(dishId));
    }

    /***
     /**
     * Creates a new dish
     * @param newDish The dish object to create
     * @return Status code 201
     * @throws InstanceAlreadyExistsException if a dish with the same id already exists
     */
    @PostMapping
    @Operation(summary = "Create a new dish", description = "Adds a new dish with its information and returns a JSON object with the status code 201.")
    public ResponseEntity<Dish> createDish(@RequestBody Dish newDish) throws InstanceAlreadyExistsException {
        return ResponseEntity.status(201).body(dishService.addDish(newDish));
    }

    /***
     /**
     * Updates an existing dish
     * @param dishId The id of the dish to update
     * @param dish Updated dish data
     * @return Status code 200
     * @throws InstanceNotFoundException if the dish with the specified id is not found
     * @throws InstanceAlreadyExistsException if a dish with the same id already exists
     */
    @PutMapping(value = "/{dishId}")
    @Operation(summary = "Update a dish", description = "Updates the information of an existing dish and returns a JSON object with the status code 200.")
    public ResponseEntity<Dish> updateDish(@PathVariable("dishId") int dishId, @RequestBody Dish dish) throws InstanceNotFoundException, InstanceAlreadyExistsException {
        return ResponseEntity.status(200).body(dishService.updateById(dishId, dish));
    }

    /***
     /**
     * Deletes a dish by its id
     * @param dishId Used to delete a certain dish
     * @return "Success" message for deleting a dish
     * @throws InstanceNotFoundException
     * @throws InstanceNotFoundException if the dish with the specified id is not found
     */
    @DeleteMapping("{dishId}")
    @Operation(summary = "Delete a dish", description = "Removes a dish using its id and returns a message with the status code 200.")
    public String deleteDish(@PathVariable("dishId") int dishId) throws InstanceNotFoundException {
        dishService.deleteDish(dishId);
        return "Dish with id " + dishId + " was successfully deleted.";
    }

    /***
     /**
     * Handles InstanceNotFoundException
     * @param infe The exception to handle
     * @return Status Code 404 and error message
     */
    @ExceptionHandler(InstanceNotFoundException.class)
    public ResponseEntity<String> instanceNotFoundException(InstanceNotFoundException infe) {
        return ResponseEntity.status(404).body(infe.getMessage());
    }

    /***
     /**
     * Handles InstanceAlreadyExistsException
     * @param iaee The exception to handle
     * @return Status Code 404 and error message
     */
    @ExceptionHandler(InstanceAlreadyExistsException.class)
    public ResponseEntity<String> instanceAlreadyExistException(InstanceAlreadyExistsException iaee) {
        return ResponseEntity.status(404).body(iaee.getMessage());
    }
}