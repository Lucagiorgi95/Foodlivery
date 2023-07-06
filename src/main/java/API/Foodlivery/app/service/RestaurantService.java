package API.Foodlivery.app.service;

import API.Foodlivery.app.entities.Address;
import API.Foodlivery.app.entities.dto.DrinksDTO;
import API.Foodlivery.app.entities.dto.FoodDTO;
import API.Foodlivery.app.entities.rto.RestaurantRTO;
import API.Foodlivery.app.repositories.AddressRepository;
import API.Foodlivery.app.tools.Converters;
import API.Foodlivery.app.entities.dto.RestaurantDTO;
import API.Foodlivery.app.entities.Restaurant;
import API.Foodlivery.app.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AddressService addressService;
    @Autowired
    FoodService foodService;
    @Autowired
    DrinkService drinkService;
    @Autowired
    Converters converters;

    public RestaurantDTO createNewRestaurant(RestaurantDTO dto){
        Restaurant entity = new Restaurant();
            entity.setName(dto.getName());
            entity.setType(dto.getType());
        Address address = addressService.createAddress(dto.getAddress());
            addressRepository.save(address);
        entity.setAddress(address);
        restaurantRepository.save(entity);
        return converters.restaurantFromEntityToDto(entity);
    }

    public List<RestaurantRTO> searchForType(String type){
        List<Restaurant> listForType = restaurantRepository.findAllByType(type);
        if(listForType.isEmpty()) throw new RuntimeException("No restaurants found for: " + type);
        return converters.listRestaurantRTO(listForType);
    }

    public RestaurantRTO findRestaurant(long idRestaurnat) {
        Restaurant restaurant = restaurantRepository.getById(idRestaurnat);
        return converters.restaurantFromEntityToRTO(restaurant);
    }

    public List<Object> createCatalogue(long idRestaurnat) {
        List<Object> catalogue = new ArrayList<>();
        List<FoodDTO> foodList = foodService.findAllFoodForRestaurant(idRestaurnat);
        List<DrinksDTO> drinklist = drinkService.findAllDrinkForRestaurant(idRestaurnat);
        catalogue.add(foodList);
        catalogue.add(drinklist);
        return catalogue;
    }
}
