package API.Foodlivery.app.comon.repositories;

import API.Foodlivery.app.comon.entities.Review;
import API.Foodlivery.app.restaurants.entities.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends FoodliveryRepository<Review> {
    List<Review> findAllByRestaurantId(long id);
}
