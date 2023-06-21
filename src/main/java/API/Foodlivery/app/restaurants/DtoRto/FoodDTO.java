package API.Foodlivery.app.restaurants.DtoRto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodDTO {

    private String name;
    private double prize;
    private String description;
}
