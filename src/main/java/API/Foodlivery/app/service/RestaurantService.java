package API.Foodlivery.app.service;

import API.Foodlivery.app.entities.Address;
import API.Foodlivery.app.repositories.AddressRepository;
import API.Foodlivery.app.tools.Converters;
import API.Foodlivery.app.entities.dto.RestaurantDTO;
import API.Foodlivery.app.entities.Restaurant;
import API.Foodlivery.app.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AddressService addressService;
    @Autowired
    Converters converters;

    public RestaurantDTO createNewRestaurant(RestaurantDTO dto){
        Restaurant entity = new Restaurant();
            entity.setName(dto.getName());
            entity.setType(dto.getType());
        Address address = addressService.createAddress(dto.getAddress());
            addressRepository.save(address);
        entity.setAddress(address);
        restaurantRepository.save(entity);
        return converters.restaurantFromEntityToDto(entity);
    }
}
