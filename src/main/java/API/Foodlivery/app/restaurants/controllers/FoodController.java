package API.Foodlivery.app.restaurants.controllers;

import API.Foodlivery.app.comon.tools.CheckEmptyField;
import API.Foodlivery.app.restaurants.DtoRto.FoodDTO;
import API.Foodlivery.app.restaurants.DtoRto.FoodRequestDTO;
import API.Foodlivery.app.restaurants.service.FoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/Food")
public class FoodController {

    @Autowired
    FoodService foodService;

    @Autowired
    CheckEmptyField checkEmptyField;

    Logger LOGGER = LoggerFactory.getLogger(FoodController.class);

    @PostMapping("/create-new-food")
    public ResponseEntity insertNewFood(@RequestBody FoodRequestDTO dto){
        LOGGER.info("Checking empty fields...");
        for(FoodDTO x : dto.getListOfFood()){
            HashSet<String> errors = checkEmptyField.checkRegisterFood(x);
            if(!errors.isEmpty()) return ResponseEntity.badRequest().body(errors);
        }
        LOGGER.info("Check complete");

        try{
            foodService.saveFood(dto);
            LOGGER.info("All your products have been successfully saved");
            return ResponseEntity.ok().body("All your products have been added");
        }catch (Exception ex){
            LOGGER.error(ex.getMessage());
            return ResponseEntity.internalServerError().body(ex);
        }

    }
}
