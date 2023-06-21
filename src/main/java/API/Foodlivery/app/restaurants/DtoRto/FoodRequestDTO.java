package API.Foodlivery.app.restaurants.DtoRto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodRequestDTO {

    private long restaurantID;
    private List<FoodDTO> listOfFood;
}
