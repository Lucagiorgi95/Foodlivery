package API.Foodlivery.app.restaurants.entities;

import API.Foodlivery.app.comon.entities.Address;
import API.Foodlivery.app.restaurants.entities.type.RestaurantType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private RestaurantType type;
    private int starReview;
    @OneToOne
    private Address address;
}
