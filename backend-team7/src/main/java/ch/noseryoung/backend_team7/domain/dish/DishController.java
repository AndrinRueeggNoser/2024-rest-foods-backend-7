package ch.noseryoung.backend_team7.domain.dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired DishService dishService;

    // Implement Swagger! @Operation
    @GetMapping
    public List<Dish> getAllDishes(){
        return dishService.getAllDishes();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dish createDish(@RequestBody Dish newDish){
        return dishService.addDish(newDish);
    }





}
