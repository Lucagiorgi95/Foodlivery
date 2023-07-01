package API.Foodlivery.app.entities.dto;

import API.Foodlivery.app.entities.dto.AddressDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String name;
    private String surname;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate DateOfBirth;
    private String email;
    private String password;
    private String telephoneNumber;
    private AddressDTO address;

}
