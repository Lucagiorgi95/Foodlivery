package API.Foodlivery.app.service;

import API.Foodlivery.app.entities.Address;
import API.Foodlivery.app.repositories.AddressRepository;
import API.Foodlivery.app.tools.Converters;
import API.Foodlivery.app.entities.User;
import API.Foodlivery.app.entities.dto.UserDTO;
import API.Foodlivery.app.entities.rto.UserRTO;
import API.Foodlivery.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AddressService addressService;
    @Autowired
    Converters converters;

    public UserRTO createUser(UserDTO dto){
        User user = converters.userFromDtoToEntity(dto);
        Address address = addressService.createAddress(dto.getAddress());
        address.setUser(user);
        addressRepository.save(address);
        user.setPassword(dto.getPassword());
        user.setAddress(address);
        userRepository.save(user);
        return converters.userFromEntityToDto(user);
    }

    public UserRTO getUser(long id){
        User user = userRepository.getById(id);
        if(user != null) return converters.userFromEntityToDto(user);
        else return null;
    }

    public boolean deleteUser(long id){
        User user = userRepository.getById(id);
        if (user != null) {
            userRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

}
