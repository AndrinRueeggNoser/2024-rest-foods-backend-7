package ch.noseryoung.backend_team7.domain.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
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

    public Dish getById(int dishId) throws InstanceNotFoundException {
        return dishRepository.findById(dishId)
                .orElseThrow(() -> new InstanceNotFoundException("Dish with id " + dishId + " could not be found."));
    }

    public Dish addDish(Dish newDish) throws InstanceAlreadyExistsException {
        Dish existingDish = dishRepository.findByName(newDish.getDishName());
        if (existingDish != null) {
            throw new InstanceAlreadyExistsException("A dish like this already exists. Please check the other dishes!");
        }
        return dishRepository.save(newDish);
    }

    public Dish updateById(int dishId, Dish dish) throws InstanceNotFoundException, InstanceAlreadyExistsException {
        Dish existingDish = dishRepository.findByName(dish.getDishName());
        if (!dishRepository.existsById(dishId)) {
            throw new InstanceNotFoundException("Dish with id " + dishId + " could not be found.");
        } else if (existingDish != null) {
            throw new InstanceAlreadyExistsException("A dish like this already exists. Please check the other dishes!");
        }
        dish.setDishId(dishId);
        return dishRepository.save(dish);
    }

    public void deleteDish(int id) throws InstanceNotFoundException {
        if (!dishRepository.existsById(id)) {
            throw new InstanceNotFoundException("Dish with id " + id + " could not be found.");
        }
        dishRepository.deleteById(id);
    }
}
