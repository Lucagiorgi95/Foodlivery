package API.Foodlivery.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String streetName;
    private int streetNumber;
    private String postcode;
    private String city;
    private String state;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "address")
    @JsonIgnore
    private User user;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "address")
    @JsonIgnore
    private Restaurant restaurant;
}
