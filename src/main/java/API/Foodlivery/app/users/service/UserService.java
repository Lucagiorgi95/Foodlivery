package API.Foodlivery.app.users.service;

import API.Foodlivery.app.comon.entities.Address;
import API.Foodlivery.app.comon.repositories.AddressRepository;
import API.Foodlivery.app.comon.service.AddressService;
import API.Foodlivery.app.comon.tools.Converters;
import API.Foodlivery.app.users.entities.User;
import API.Foodlivery.app.users.entities.dto.UserDTO;
import API.Foodlivery.app.users.entities.rto.UserRTO;
import API.Foodlivery.app.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    PasswordEncoder passwordEncoder;

    public UserRTO createUser(UserDTO dto){
        User user = converters.userFromDtoToEntity(dto);
        Address address = addressService.createAddress(dto.getAddress());
        address.setUser(user);
        addressRepository.save(address);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAddress(address);
        userRepository.save(user);
        return converters.userFromEntityToDto(user);
    }

    public UserRTO getUser(long id){
        User user = userRepository.getById(id);
        if(user != null) return converters.userFromEntityToDto(user);
        else return null;
    }

}
