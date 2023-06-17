package API.Foodlivery.app.comon.tools;

import API.Foodlivery.app.comon.dto.AddressDTO;
import API.Foodlivery.app.comon.entities.Address;
import API.Foodlivery.app.users.entities.User;
import API.Foodlivery.app.users.entities.dto.UserDTO;
import API.Foodlivery.app.users.entities.rto.UserRTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Converters {
    @Autowired
    Converters converters;

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
        entity.setPassword(userDTO.getPassword());
        entity.setTelephoneNumber(userDTO.getTelephoneNumber());
        return entity;
    }

    public UserRTO userFromEntityToDto(User user){
        UserRTO rto = new UserRTO();
        AddressDTO dto = converters.addressFromEntityToDto(user.getAddress());
        rto.setId(user.getId());
        rto.setName(user.getName());
        rto.setSurname(user.getSurname());
        rto.setEmail(user.getEmail());
        rto.setDateOfBirth(user.getDateOfBirth());
        rto.setTelephoneNumber(user.getTelephoneNumber());
        rto.setAddress(dto);
        return rto;
    }
}
