package ch.noseryoung.backend_team7.domain.dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dishes")
public class DishController {
    @Autowired DishService dishService;

    // Implement Swagger! @Operation
    @GetMapping
    public List<Dish> getAllDishes(){
        return dishService.getAllDishes();
    }


}
