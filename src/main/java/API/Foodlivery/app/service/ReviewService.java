package API.Foodlivery.app.service;

import API.Foodlivery.app.entities.dto.ReviewDTO;
import API.Foodlivery.app.entities.rto.ReviewRTO;
import API.Foodlivery.app.entities.Review;
import API.Foodlivery.app.repositories.ReviewRepository;
import API.Foodlivery.app.tools.Converters;
import API.Foodlivery.app.entities.Restaurant;
import API.Foodlivery.app.repositories.RestaurantRepository;
import API.Foodlivery.app.entities.User;
import API.Foodlivery.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    Converters converters;

    public ReviewRTO saveNewReview(ReviewDTO dto){
        Review entity = new Review();
        entity.setStar(dto.getStar());
        entity.setDescription(dto.getDescription());
        addReviewToUser(entity,dto);
        addReviewToRestaurant(entity,dto);
        reviewRepository.save(entity);
        updateStarRestaurant(entity.getRestaurant());
        return converters.reviewFromEntityYoDto(entity);
    }

    public void addReviewToUser(Review review, ReviewDTO dto){
        User user = userRepository.getById(dto.getUserId());
        review.setUser(user);
        reviewRepository.save(review);
        user.addListOfReview(review);
        userRepository.save(user);
    }

    public void addReviewToRestaurant(Review review, ReviewDTO dto){
        Restaurant restaurant = restaurantRepository.getById(dto.getRestaurantId());
        review.setRestaurant(restaurant);
        reviewRepository.save(review);
        restaurant.addReview(review);
        restaurantRepository.save(restaurant);
    }

    public void updateStarRestaurant(Restaurant restaurant){
        List<Review> reviews = reviewRepository.findAllByRestaurantId(restaurant.getId());
        int totalStar = 0;
        for(Review x : reviews){
            totalStar = x.getStar() + totalStar;
        }
        int averageStar = totalStar/reviews.size();
        restaurant.setStarReview(averageStar);
        restaurantRepository.save(restaurant);
    }
}
