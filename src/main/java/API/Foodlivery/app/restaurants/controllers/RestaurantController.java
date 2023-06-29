package API.Foodlivery.app.restaurants.controllers;

import API.Foodlivery.app.comon.tools.CheckEmptyField;
import API.Foodlivery.app.restaurants.DtoRto.RestaurantDTO;
import API.Foodlivery.app.restaurants.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
@RequestMapping("/Restaurant")
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;
    @Autowired
    CheckEmptyField checkEmptyField;

    Logger LOGGER = LoggerFactory.getLogger(RestaurantController.class);

    @PostMapping("/create-restaurant")
    public ResponseEntity insertNewRestaurant(@RequestBody RestaurantDTO dto){
        LOGGER.info("Checking empty fields...");
        HashSet<String> errors = checkEmptyField.checkRegisterRestaurant(dto);
        if(!errors.isEmpty()) return ResponseEntity.badRequest().body(errors);
        LOGGER.info("Check complete");

        try{
            RestaurantDTO restaurant = restaurantService.createNewRestaurant(dto);
            LOGGER.info("Your restaurant " + restaurant.getName() + " has been successfully saved");
            return ResponseEntity.ok().body("The restaurant is been created");
        }catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return ResponseEntity.internalServerError().body(ex);
        }
    }
}
