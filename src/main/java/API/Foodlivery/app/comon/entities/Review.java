package API.Foodlivery.app.comon.entities;

import API.Foodlivery.app.restaurants.entities.Restaurant;
import API.Foodlivery.app.users.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    private Restaurant restaurant;
    private int star;
    private String description;

}
