package API.Foodlivery.app.users.entities;

import API.Foodlivery.app.comon.entities.Address;
import API.Foodlivery.app.comon.entities.Review;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate DateOfBirth;
    private String email;
    private String password;
    private String telephoneNumber;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    @OneToMany
    private List<Review> listOfReview;

    public void addListOfReview(Review listOfReview) {
        this.listOfReview.add(listOfReview);
    }
}
