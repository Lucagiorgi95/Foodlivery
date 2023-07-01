package API.Foodlivery.app.repositories;

import API.Foodlivery.app.repositories.FoodliveryRepository;
import API.Foodlivery.app.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends FoodliveryRepository<User> {
}
