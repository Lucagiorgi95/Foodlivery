package API.Foodlivery.app.entities.dto;

import API.Foodlivery.app.type.RestaurantType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {

    private String name;
    private RestaurantType type;
    private AddressDTO address;

}
