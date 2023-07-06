package API.Foodlivery.app.service;

import API.Foodlivery.app.repositories.AddressRepository;
import API.Foodlivery.app.tools.Converters;
import API.Foodlivery.app.entities.dto.AddressDTO;
import API.Foodlivery.app.entities.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    Converters converters;
    @Autowired
    AddressRepository addressRepository;

    public Address createAddress(AddressDTO dto){
        return  converters.addressFromDtoToEntity(dto);
    }


    public Address updateAddress(AddressDTO addressDTO, long id) {
        Address address = addressRepository.getById(id);
        if(addressDTO.getStreetName() != null) address.setStreetName(addressDTO.getStreetName());
        if(addressDTO.getStreetNumber() != 0) address.setStreetNumber(addressDTO.getStreetNumber());
        if(addressDTO.getPostcode() != null) address.setPostcode(addressDTO.getPostcode());
        if(addressDTO.getCity() != null) address.setCity(addressDTO.getCity());
        if(addressDTO.getState() != null) address.setState(addressDTO.getState());
        addressRepository.save(address);
        return address;
    }
}
