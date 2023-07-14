package API.Foodlivery.app.tools;

import API.Foodlivery.app.entities.*;
import API.Foodlivery.app.entities.dto.*;
import API.Foodlivery.app.entities.rto.RestaurantRTO;
import API.Foodlivery.app.entities.rto.ReviewRTO;
import API.Foodlivery.app.entities.rto.UserRTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Converters {

    //Converter Address
    public Address addressFromDtoToEntity(AddressDTO dto){
        Address entity = new Address();
        entity.setStreetName(dto.getStreetName());
        entity.setStreetNumber(dto.getStreetNumber());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        entity.setPostcode(dto.getPostcode());
        return entity;
    }

    public AddressDTO addressFromEntityToDto (Address entity){
        AddressDTO rto = new AddressDTO();
        rto.setStreetName(entity.getStreetName());
        rto.setStreetNumber(entity.getStreetNumber());
        rto.setCity(entity.getCity());
        rto.setState(entity.getState());
        rto.setPostcode(entity.getPostcode());
        return rto;
    }

    //Converter User
    public User userFromDtoToEntity(UserDTO userDTO){
        User entity = new User();
        entity.setName(userDTO.getName());
        entity.setSurname(userDTO.getSurname());
        entity.setDateOfBirth(userDTO.getDateOfBirth());
        entity.setEmail(userDTO.getEmail());
        entity.setTelephoneNumber(userDTO.getTelephoneNumber());
        return entity;
    }

    public UserRTO userFromEntityToDto(User user){
        UserRTO rto = new UserRTO();
        AddressDTO dto = addressFromEntityToDto(user.getAddress());
        rto.setId(user.getId());
        rto.setName(user.getName());
        rto.setSurname(user.getSurname());
        rto.setEmail(user.getEmail());
        rto.setDateOfBirth(user.getDateOfBirth());
        rto.setTelephoneNumber(user.getTelephoneNumber());
        rto.setAddress(dto);
        return rto;
    }

    //Converter Restaurant
    public RestaurantDTO restaurantFromEntityToDto(Restaurant restaurant){
        RestaurantDTO rto = new RestaurantDTO();
        AddressDTO dto = addressFromEntityToDto(restaurant.getAddress());
        rto.setName(restaurant.getName());
        rto.setType(restaurant.getType());
        rto.setAddress(dto);
        return rto;
    }

    public RestaurantRTO restaurantFromEntityToRTO(Restaurant restaurant){
        RestaurantRTO rto = new RestaurantRTO();
        rto.setName(restaurant.getName());
        rto.setStar(restaurant.getStarReview());
        rto.setType(restaurant.getType());
        return rto;
    }

    public List<RestaurantRTO> listRestaurantRTO (List<Restaurant> restaurants){
        List<RestaurantRTO> list = new ArrayList<>();
        for(Restaurant x : restaurants){
            list.add(restaurantFromEntityToRTO(x));
        }
        return list;
    }

    //Converter Review
    public ReviewRTO reviewFromEntityYoDto(Review review){
        ReviewRTO rto = new ReviewRTO();
        rto.setStar(review.getStar());
        rto.setDescription(review.getDescription());
        return rto;
    }

    public List<ReviewRTO> reviewsListFromEntityToDto(List<Review> reviewList){
        List<ReviewRTO> rtoList = new ArrayList<>();
        for(Review x : reviewList){
            ReviewRTO reviewRTO = reviewFromEntityYoDto(x);
            rtoList.add(reviewRTO);
        }
        return rtoList;
    }

    //Converter Food
    public FoodDTO foodFromEntityToDto(Food food){
        FoodDTO dto = new FoodDTO();
        dto.setName(food.getName());
        dto.setPrize(food.getPrize());
        dto.setDescription(food.getDescription());
        return dto;
    }

    public List<FoodDTO> listFoodFromEntityToDto(List<Food> list){
        List<FoodDTO> dtoList = new ArrayList<>();
        for(Food x : list){
            dtoList.add(foodFromEntityToDto(x));
        }
        return dtoList;
    }

    //Converter Drink
    public List<DrinksDTO> listDrinksFromEntityToDto(List<Drink> drinks) {
        List<DrinksDTO> dtoList = new ArrayList<>();
        for(Drink x : drinks){
            dtoList.add(DrinkFromEntityToDto(x));
        }
        return dtoList;
    }

    private DrinksDTO DrinkFromEntityToDto(Drink drink) {
        DrinksDTO dto = new DrinksDTO();
        dto.setName(drink.getName());
        dto.setPrize(drink.getPrize());
        dto.setDescription(drink.getDescription());
        return dto;
    }
}
