package API.Foodlivery.app.restaurants.repositories;

import API.Foodlivery.app.comon.repositories.FoodliveryRepository;
import API.Foodlivery.app.restaurants.entities.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends FoodliveryRepository<Restaurant> {
}
