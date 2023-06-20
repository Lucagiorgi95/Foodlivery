package API.Foodlivery.app.restaurants.DtoRto;

import API.Foodlivery.app.comon.dto.AddressDTO;
import API.Foodlivery.app.restaurants.entities.type.RestaurantType;
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
