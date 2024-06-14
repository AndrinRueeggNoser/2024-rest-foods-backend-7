package ch.noseryoung.backend_team7.domain.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DishService {
    @Autowired
    DishRepository dishRepository;

    public DishService() {

    }


    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public Dish getById(int id) {
        return dishRepository.findById(id).orElseThrow(() -> new NoSuchElementException("GET: Dish with id " + id + " could not be found."));
    }


    public Dish addDish(Dish newDish) {
        return dishRepository.save(newDish);
    }

    public Dish updateById(int dishId, Dish dish) {
        dish.setDishId(dishId);
        return dishRepository.save(dish);
    }
}
