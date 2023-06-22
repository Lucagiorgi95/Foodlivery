package API.Foodlivery.app.restaurants.entities;

import API.Foodlivery.app.comon.entities.Address;
import API.Foodlivery.app.comon.entities.Review;
import API.Foodlivery.app.restaurants.entities.type.RestaurantType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @OneToMany
    private List<Food> foods;
    @OneToMany(mappedBy = "restaurant")
    private List<Review> review;

    public void addFoods(List<Food> foods) {
        for(Food x : foods){
            this.foods.add(x);
        }
    }

    public void addReview(Review reviews){
        this.review.add(reviews);
    }
}
