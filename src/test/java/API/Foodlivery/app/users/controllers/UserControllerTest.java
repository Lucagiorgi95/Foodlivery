package API.Foodlivery.app.users.controllers;

import API.Foodlivery.app.users.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Autowired
    UserController userController;

    User User = new User()
    @Test
    void createUser() {
        userController.createUser()

    }

    @Test
    void viewUser() {
    }
}