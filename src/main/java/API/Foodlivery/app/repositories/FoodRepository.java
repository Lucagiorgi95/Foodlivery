package API.Foodlivery.app.repositories;

import API.Foodlivery.app.repositories.FoodliveryRepository;
import API.Foodlivery.app.entities.Food;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends FoodliveryRepository<Food> {

}
