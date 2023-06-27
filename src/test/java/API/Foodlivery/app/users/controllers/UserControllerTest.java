package API.Foodlivery.app.users.controllers;

import API.Foodlivery.app.users.entities.User;
import API.Foodlivery.app.users.entities.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Autowired
    UserController userController;

    UserDTO user (){
        UserDTO user = new UserDTO();
        user.setName("Luca");
        user.setSurname("Giorgi");
        user.setEmail("pippo@gmail.com");
        user.setPassword("password");
        user.setTelephoneNumber("3345466578");
        user.setDateOfBirth(LocalDate.ofEpochDay(10-02-1995));
        return user;
    }

    @Test
    void createUser() {

        UserDTO user = user();
        userController.createUser(user);


    }

    @Test
    void viewUser() {
    }
}