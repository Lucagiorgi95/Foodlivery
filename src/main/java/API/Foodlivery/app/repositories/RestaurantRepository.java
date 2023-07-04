package API.Foodlivery.app.repositories;

import API.Foodlivery.app.repositories.FoodliveryRepository;
import API.Foodlivery.app.entities.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends FoodliveryRepository<Restaurant> {

    List<Restaurant> findAllByType(String type);
}
