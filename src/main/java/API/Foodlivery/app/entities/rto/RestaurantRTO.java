package API.Foodlivery.app.entities.rto;

import API.Foodlivery.app.type.RestaurantType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantRTO {

    private String name;
    private int star;
    private RestaurantType type;
}
