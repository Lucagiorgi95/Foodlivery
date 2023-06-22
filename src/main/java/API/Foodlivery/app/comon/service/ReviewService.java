package API.Foodlivery.app.comon.service;

import API.Foodlivery.app.comon.dto.ReviewDTO;
import API.Foodlivery.app.comon.dto.ReviewRTO;
import API.Foodlivery.app.comon.entities.Review;
import API.Foodlivery.app.comon.repositories.ReviewRepository;
import API.Foodlivery.app.comon.tools.Converters;
import API.Foodlivery.app.restaurants.entities.Restaurant;
import API.Foodlivery.app.restaurants.repositories.RestaurantRepository;
import API.Foodlivery.app.users.entities.User;
import API.Foodlivery.app.users.repositories.UserRepository;
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
        User user = userRepository.getById(dto.getUserId());
        entity.setUser(user);

        Restaurant restaurant = restaurantRepository.getById(dto.getRestaurantId());
        entity.setRestaurant(restaurant);

        entity.setStar(dto.getStar());
        entity.setDescription(dto.getDescription());

        List<Review> listOfReview = user.getListOfReview();
        listOfReview.add(entity);
        user.addListOfReview(listOfReview);
        restaurant.addReview(entity);
        userRepository.save(user);
        restaurantRepository.save(restaurant);
        reviewRepository.save(entity);

        ReviewRTO rto = converters.reviewFromEntityYoDto(entity);
        return rto;
    }



}
