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

    public void deleteUser(long id){
        userRepository.deleteById(id);
    }

    public void updateUser(UserDTO userDTO, long id) {
        User user = userRepository.getById(id);
        long idAddress = user.getAddress().getId();
        if(userDTO.getSurname() != null) user.setSurname(userDTO.getSurname());
        if(userDTO.getEmail() != null) user.setEmail(userDTO.getEmail());
        if(userDTO.getDateOfBirth() != null) user.setDateOfBirth(userDTO.getDateOfBirth());
        if(userDTO.getTelephoneNumber() != null) user.setTelephoneNumber(userDTO.getTelephoneNumber());
        if(userDTO.getPassword() != null) user.setPassword(userDTO.getPassword());
        if(userDTO.getAddress() != null) user.setAddress(addressService.updateAddress(userDTO.getAddress(), idAddress));
        userRepository.save(user);
    }
}
