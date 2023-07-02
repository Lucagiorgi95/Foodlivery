package API.Foodlivery.app.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrinkRequestDTO {
    private long restaurantID;
    private List<DrinksDTO> listOfDrinks;
}
