package API.Foodlivery.app.repositories;

import API.Foodlivery.app.entities.Review;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends FoodliveryRepository<Review> {
    List<Review> findAllByRestaurantId(long id);

    List<Review> findAllByUserId(long Id);
}
