package API.Foodlivery.app.service;

import API.Foodlivery.app.entities.Drink;
import API.Foodlivery.app.entities.Restaurant;
import API.Foodlivery.app.entities.dto.DrinkRequestDTO;
import API.Foodlivery.app.entities.dto.DrinksDTO;
import API.Foodlivery.app.repositories.DrinkRepository;
import API.Foodlivery.app.repositories.RestaurantRepository;
import API.Foodlivery.app.tools.Converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DrinkService {

    @Autowired
    DrinkRepository drinkRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    Converters converters;

    public void saveDrinks(DrinkRequestDTO dto){
        Restaurant restaurant = restaurantRepository.getById(dto.getRestaurantID());
        List<Drink> drinks = new ArrayList<>();
        for(DrinksDTO x : dto.getListOfDrinks()){
            Drink entity = new Drink();
            entity.setName(x.getName());
            entity.setPrize(x.getPrize());
            entity.setDescription(x.getDescription());
            entity.setRestaurant(restaurant);
            drinks.add(entity);
        }
        drinkRepository.saveAll(drinks);
        restaurant.addDrinks(drinks);
        restaurantRepository.save(restaurant);
    }

    public List<DrinksDTO> findAllDrinkForRestaurant(long idRestaurnat) {
        List<Drink> drinks = drinkRepository.findAllByRestaurantId(idRestaurnat);
        return converters.listDrinksFromEntityToDto(drinks);
    }
}
