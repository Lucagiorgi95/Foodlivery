package API.Foodlivery.app.tools;

import API.Foodlivery.app.entities.dto.ReviewDTO;
import API.Foodlivery.app.entities.dto.FoodDTO;
import API.Foodlivery.app.entities.dto.RestaurantDTO;
import API.Foodlivery.app.entities.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class CheckEmptyField {

    public HashSet<String> checkRegisterUser(UserDTO dto){
        HashSet<String> errors = new HashSet<>();
        if(dto.getName() == null) errors.add("Name is not insert");
        if(dto.getSurname() == null) errors.add("Surname is not insert");
        if(dto.getDateOfBirth() == null) errors.add("Date of birt is not insert");
        if(dto.getEmail() == null) errors.add("Email is not insert");
        if(dto.getPassword() == null) errors.add("Password is not insert");
        if(dto.getAddress() == null) errors.add("Address is not insert");
        return errors;
    }

    public HashSet<String> checkRegisterRestaurant(RestaurantDTO dto){
        HashSet<String> errors = new HashSet<>();
        if(dto.getName() == null) errors.add("Name is not insert");
        if (dto.getType() == null) errors.add("Type is not insert");
        if (dto.getAddress() == null) errors.add("Address is not insert");
        return errors;
    }

    public HashSet<String> checkRegisterFood (FoodDTO dto){
        HashSet<String> errors = new HashSet<>();
        if (dto.getName() == null) errors.add("Name is not insert");
        if (dto.getPrize() == 0) errors.add("Prize is not insert");
        if (dto.getDescription() == null) errors.add("Description is not insert");
        return errors;
    }

    public HashSet<String> checkRegisterReview (ReviewDTO dto){
        HashSet<String> errors = new HashSet<>();
        if (dto.getUserId() == 0) errors.add("UserID is not insert");
        if (dto.getRestaurantId() == 0) errors.add("RestaurantID is not insert");
        if (dto.getStar() == 0) errors.add("Star rating is not insert");
        if (dto.getDescription() == null) errors.add("Description is not insert");
        return errors;
    }
}
