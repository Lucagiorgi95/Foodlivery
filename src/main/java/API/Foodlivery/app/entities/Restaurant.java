package API.Foodlivery.app.entities;

import API.Foodlivery.app.type.RestaurantType;
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
        this.foods.addAll(foods);
    }

    public void addReview(Review reviews){
        this.review.add(reviews);
    }
}
