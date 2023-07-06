package API.Foodlivery.app.repositories;

import API.Foodlivery.app.entities.Drink;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinkRepository extends FoodliveryRepository<Drink> {

    List<Drink> findAllByRestaurantId(long restaurantId);
}
