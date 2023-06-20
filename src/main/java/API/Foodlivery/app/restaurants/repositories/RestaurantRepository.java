package API.Foodlivery.app.restaurants.repositories;

import API.Foodlivery.app.comon.repositories.FoodliveryRepository;
import API.Foodlivery.app.restaurants.entities.Restaurant;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends FoodliveryRepository<Restaurant> {
}
