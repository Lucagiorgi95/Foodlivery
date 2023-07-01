package API.Foodlivery.app.service;

import API.Foodlivery.app.tools.Converters;
import API.Foodlivery.app.entities.dto.AddressDTO;
import API.Foodlivery.app.entities.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    Converters converters;

    public Address createAddress(AddressDTO dto){
        return  converters.addressFromDtoToEntity(dto);
    }



}
