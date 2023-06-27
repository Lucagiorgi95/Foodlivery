package API.Foodlivery.app.users.controllers;

import API.Foodlivery.app.comon.tools.CheckEmptyField;
import API.Foodlivery.app.users.entities.dto.UserDTO;
import API.Foodlivery.app.users.entities.rto.UserRTO;
import API.Foodlivery.app.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    CheckEmptyField checkEmptyField;

    @PostMapping("/register-user")
    public ResponseEntity createUser(@RequestBody UserDTO dto){
        HashSet<String> errors = checkEmptyField.checkRegisterUser(dto);
        if (!errors.isEmpty()) return ResponseEntity.badRequest().body(errors);

        try{
            UserRTO newUser = userService.createUser(dto);
            return ResponseEntity.ok().body("The user with ID: " + newUser.getId() + " has been registered");
        }catch (Exception ex){
            ex.getStackTrace();
            return ResponseEntity.internalServerError().body(ex);
        }
    }

    @GetMapping("/view-user")
    public ResponseEntity viewUser(@RequestParam long id){
        try{
            UserRTO user = userService.getUser(id);
            if(user == null) return ResponseEntity.badRequest().body("The user whit ID: " + id + "is not exist");
            return ResponseEntity.ok(user);
        }catch (Exception ex){
            ex.getStackTrace();
            return ResponseEntity.internalServerError().body("Unable to find user");
        }
    }



}
