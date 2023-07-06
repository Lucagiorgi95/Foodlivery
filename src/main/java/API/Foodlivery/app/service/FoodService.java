package API.Foodlivery.app.service;

import API.Foodlivery.app.entities.dto.FoodDTO;
import API.Foodlivery.app.entities.dto.FoodRequestDTO;
import API.Foodlivery.app.entities.Food;
import API.Foodlivery.app.entities.Restaurant;
import API.Foodlivery.app.repositories.FoodRepository;
import API.Foodlivery.app.repositories.RestaurantRepository;
import API.Foodlivery.app.tools.Converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    FoodRepository foodRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    Converters converters;

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

    public List<FoodDTO> findAllFoodForRestaurant(long idRestaurant){
        List<Food> foodList = foodRepository.findAllByRestaurantId(idRestaurant);
        return converters.listFoodFromEntityToDto(foodList);
    }

    public FoodDTO findById(long id) {
        try{
            Food food = foodRepository.getById(id);
            return converters.foodFromEntityToDto(food);
        }catch (Exception ex){
            return null;
        }
    }
}
