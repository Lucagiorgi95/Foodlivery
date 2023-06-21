package API.Foodlivery.app.restaurants.repositories;

import API.Foodlivery.app.comon.repositories.FoodliveryRepository;
import API.Foodlivery.app.restaurants.DtoRto.FoodDTO;
import API.Foodlivery.app.restaurants.entities.Food;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends FoodliveryRepository<Food> {
    @Override
    <S extends Food> List<S> saveAll(Iterable<S> entities);
}
