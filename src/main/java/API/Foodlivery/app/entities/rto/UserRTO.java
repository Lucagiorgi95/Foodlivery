package API.Foodlivery.app.entities.rto;

import API.Foodlivery.app.entities.dto.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRTO {
    private long id;
    private String name;
    private String surname;
    private LocalDate DateOfBirth;
    private String email;
    private String telephoneNumber;
    private AddressDTO address;
}
