package API.Foodlivery.app.users.repositories;

import API.Foodlivery.app.comon.repositories.FoodliveryRepository;
import API.Foodlivery.app.users.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends FoodliveryRepository<User> {
}
