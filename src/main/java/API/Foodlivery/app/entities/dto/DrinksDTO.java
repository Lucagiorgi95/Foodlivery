package API.Foodlivery.app.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrinksDTO {
    private String name;
    private double prize;
    private String description;
}
