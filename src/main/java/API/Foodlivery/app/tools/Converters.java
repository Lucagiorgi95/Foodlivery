package API.Foodlivery.app.tools;

import API.Foodlivery.app.entities.dto.AddressDTO;
import API.Foodlivery.app.entities.rto.ReviewRTO;
import API.Foodlivery.app.entities.Address;
import API.Foodlivery.app.entities.Review;
import API.Foodlivery.app.entities.dto.RestaurantDTO;
import API.Foodlivery.app.entities.Restaurant;
import API.Foodlivery.app.entities.User;
import API.Foodlivery.app.entities.dto.UserDTO;
import API.Foodlivery.app.entities.rto.UserRTO;
import org.springframework.stereotype.Service;

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

    //Converter Review
    public ReviewRTO reviewFromEntityYoDto(Review review){
        ReviewRTO rto = new ReviewRTO();
        rto.setStar(review.getStar());
        rto.setDescription(review.getDescription());
        return rto;
    }

}
