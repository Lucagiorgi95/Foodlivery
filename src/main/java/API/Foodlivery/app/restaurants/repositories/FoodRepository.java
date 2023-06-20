package API.Foodlivery.app.restaurants.repositories;

import API.Foodlivery.app.comon.repositories.FoodliveryRepository;
import API.Foodlivery.app.restaurants.entities.Food;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends FoodliveryRepository<Food> {
}
