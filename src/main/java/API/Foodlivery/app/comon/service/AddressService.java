package API.Foodlivery.app.comon.service;

import API.Foodlivery.app.comon.tools.Converters;
import API.Foodlivery.app.comon.dto.AddressDTO;
import API.Foodlivery.app.comon.entities.Address;
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
