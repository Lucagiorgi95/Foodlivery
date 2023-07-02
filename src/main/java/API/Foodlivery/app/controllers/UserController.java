package API.Foodlivery.app.controllers;

import API.Foodlivery.app.tools.CheckEmptyField;
import API.Foodlivery.app.entities.dto.UserDTO;
import API.Foodlivery.app.entities.rto.UserRTO;
import API.Foodlivery.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * Metodo per creare un nuovo utente con invio di un email di conferma registrazione
     *
     * @param userDTO                Oggetto da creare
     * @return                       Ritorna una response entity con status OK/BAD REQUEST/INTERNA ERRROR SERVER
     *                               a seconda dell'esito
     */
    @PostMapping("/register-user")
    public ResponseEntity createUser(@RequestBody UserDTO userDTO){
        LOGGER.info("Checking empty fields...");
        HashSet<String> errors = checkEmptyField.checkRegisterUser(userDTO);
        if (!errors.isEmpty()) return ResponseEntity.badRequest().body(errors);
        LOGGER.info("Check complete");

        try{
            UserRTO newUser = userService.createUser(userDTO);
            LOGGER.info("The user with ID: " + newUser.getId() + " was successfully saved");

            return ResponseEntity.ok().body("The user with ID: " + newUser.getId() + " has been registered");
        }catch (Exception ex){
            LOGGER.error(ex.getMessage());
            return ResponseEntity.internalServerError().body(ex);
        }
    }

    @GetMapping("/view-user")
    public ResponseEntity viewUser(@RequestParam long id){
        try{
            UserRTO user = userService.getUser(id);
            if(user == null) return ResponseEntity.badRequest().body("The user whit ID: " + id + "is not exist");
            LOGGER.info("The user whit ID: " + id + "has been found");
            return ResponseEntity.ok(user);
        }catch (Exception ex){
            LOGGER.error(ex.getMessage());
            return ResponseEntity.internalServerError().body("Unable to find user");
        }
    }



}
