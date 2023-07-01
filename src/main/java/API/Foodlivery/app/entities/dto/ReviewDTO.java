package API.Foodlivery.app.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {

    private long userId;
    private long restaurantId;
    private int star;
    private String description;
}
