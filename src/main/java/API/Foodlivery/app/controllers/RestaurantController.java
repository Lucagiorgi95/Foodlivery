package API.Foodlivery.app.controllers;

import API.Foodlivery.app.entities.rto.RestaurantRTO;
import API.Foodlivery.app.tools.CheckEmptyField;
import API.Foodlivery.app.entities.dto.RestaurantDTO;
import API.Foodlivery.app.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

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

    @GetMapping("/search-for-type/{type}")
    public ResponseEntity getForType(@RequestParam String type){
        try{
            List<RestaurantRTO> listForType = restaurantService.searchForType(type);
            LOGGER.info("Find all restaurant for: " + type);
            return ResponseEntity.ok(listForType);
        }catch (Exception ex){
            LOGGER.error(ex.getMessage());
            return ResponseEntity.internalServerError().body("Could not be searched");
        }
    }



}
