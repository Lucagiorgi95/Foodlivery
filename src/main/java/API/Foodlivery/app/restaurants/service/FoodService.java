package API.Foodlivery.app.restaurants.service;

import API.Foodlivery.app.restaurants.DtoRto.FoodDTO;
import API.Foodlivery.app.restaurants.DtoRto.FoodRequestDTO;
import API.Foodlivery.app.restaurants.entities.Food;
import API.Foodlivery.app.restaurants.entities.Restaurant;
import API.Foodlivery.app.restaurants.repositories.FoodRepository;
import API.Foodlivery.app.restaurants.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService {

    @Autowired
    FoodRepository foodRepository;
    @Autowired
    RestaurantRepository restaurantRepository;

    public void saveFood(FoodRequestDTO dto){
        Restaurant restaurant = restaurantRepository.getById(dto.getRestaurantID());
        List<Food> foods = new ArrayList<>();
        for(FoodDTO x : dto.getListOfFood()){
            Food entity = new Food();
            entity.setName(x.getName());
            entity.setPrize(x.getPrize());
            entity.setDescription(x.getDescription());
            entity.setRestaurant(restaurant);
            foods.add(entity);
        }
        foodRepository.saveAll(foods);
        restaurant.addFoods(foods);
        restaurantRepository.save(restaurant);
    }
}
