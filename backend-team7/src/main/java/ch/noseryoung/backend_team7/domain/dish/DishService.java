package ch.noseryoung.backend_team7.domain.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    @Autowired DishRepository dishRepository;
    public DishService(){

    }


    public List<Dish> getAllDishes(){
        return dishRepository.findAll();
    }
    public Dish addDish(Dish newDish){
        return dishRepository.save(newDish);
    }
}
