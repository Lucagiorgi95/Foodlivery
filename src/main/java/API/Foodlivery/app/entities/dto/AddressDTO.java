package API.Foodlivery.app.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private String streetName;
    private int streetNumber;
    private int postcode;
    private String city;
    private String state;
}
