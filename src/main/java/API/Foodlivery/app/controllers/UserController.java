package API.Foodlivery.app.controllers;

import API.Foodlivery.app.entities.dto.UserDTO;
import API.Foodlivery.app.entities.rto.UserRTO;
import API.Foodlivery.app.service.UserService;
import API.Foodlivery.app.tools.Validation;
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
    Validation validation;

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
        try{
            LOGGER.info("Checking empty fields...");
            HashSet<String> errors = validation.checkRegisterUser(userDTO);
            if (!errors.isEmpty()) return ResponseEntity.badRequest().body(errors);
            LOGGER.info("Check complete");

            UserRTO newUser = userService.createUser(userDTO);
            LOGGER.info("The user with ID: " + newUser.getId() + " was successfully saved");

            return ResponseEntity.ok().body("The user with ID: " + newUser.getId() + " has been registered");
        }catch (Exception ex){
            LOGGER.error(ex.getMessage());
            return ResponseEntity.internalServerError().body("Impossible create the user");
        }
    }

    /**
     * Controller per visualizzare un utente tramite il suo id
     *
     * @param id                      Id utente
     * @return                        Ritorna una response entity con status OK/BAD REQUEST/INTERNA ERRROR SERVER
     *                                a seconda dell'esito se OK ritorna anche l'user desiderato
     */
    @GetMapping("/view-user")
    public ResponseEntity viewUser(@RequestParam long id){
        try{
            LOGGER.info(String.format("Find user whit id: %s",id));
            UserRTO user = userService.getUser(id);
            HashSet<String> checkRto = validation.checkRtoUser(user);
            if(checkRto.isEmpty()){
                LOGGER.info(String.format("The user whit ID: %s has been found", id));
                return ResponseEntity.ok(user);
            }else{
                String error = String.format("The user whit ID: %s not exist",id);
                LOGGER.error(error);
                return ResponseEntity.badRequest().body(error);
            }
        }catch (Exception ex){
            LOGGER.error(ex.getMessage());
            return ResponseEntity.internalServerError().body("Unable to find user");
        }
    }

    /**
     * Controller per eliminare un'utente tramite il suo id
     *
     * @param id                      Id dell'utente
     * @return                        Ritorna una response entity con status OK/BAD REQUEST/INTERNA ERRROR SERVER
     *                                a seconda dell'esito
     */
    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@RequestParam long id){
        try{
            UserRTO user = userService.getUser(id);
            HashSet<String> checkRto = validation.checkRtoUser(user);
            if(checkRto.isEmpty()) {
                userService.deleteUser(id);
                String response = String.format("The user whit ID: %s is deleted", id);
                LOGGER.info(response);
                return ResponseEntity.ok().body(response);
            }else {
                String error = String.format("The user whit ID: %s not found",id);
                LOGGER.error(error);
                return ResponseEntity.badRequest().body(error);
            }
        }catch (Exception ex){
            LOGGER.error(ex.getMessage());
            return ResponseEntity.internalServerError().body("Impossible delete the user");
        }
    }

    /**
     * Metodo per modificare le proprietà dell'user
     *
     * @param userDTO                     dto con le nuove proprietà
     * @param id                          id dell'user
     * @return                            ritorna una response entity con status OK/INTERNAL ERROR SERVER a seconda dell'esito
     */
    @PutMapping("/profile-modification")
    public ResponseEntity updateProfile(@RequestBody UserDTO userDTO,@RequestParam long id){
        try{
            UserRTO user = userService.getUser(id);
            HashSet<String> checkRto = validation.checkRtoUser(user);
            if(checkRto.isEmpty()){
                userService.updateUser(userDTO, id);
                LOGGER.info("Changes were made");
                return ResponseEntity.ok().body("Success");
            }else{
                LOGGER.error("User not found");
                return ResponseEntity.badRequest().body("The user to edit was not found");
            }
        }catch (Exception ex){
            LOGGER.error(ex.getMessage());
            return ResponseEntity.internalServerError().body("Cannot change the user");
        }
    }
}
