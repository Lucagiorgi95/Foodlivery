package API.Foodlivery.app.restaurants.service;

import API.Foodlivery.app.comon.entities.Address;
import API.Foodlivery.app.comon.repositories.AddressRepository;
import API.Foodlivery.app.comon.service.AddressService;
import API.Foodlivery.app.comon.tools.Converters;
import API.Foodlivery.app.restaurants.DtoRto.RestaurantDTO;
import API.Foodlivery.app.restaurants.entities.Restaurant;
import API.Foodlivery.app.restaurants.repositories.RestaurantRepository;
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
