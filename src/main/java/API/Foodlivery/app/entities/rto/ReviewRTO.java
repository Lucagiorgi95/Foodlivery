package API.Foodlivery.app.entities.rto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRTO {
    private int star;
    private String description;
}
