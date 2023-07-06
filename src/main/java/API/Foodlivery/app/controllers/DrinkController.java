package API.Foodlivery.app.controllers;

import API.Foodlivery.app.entities.dto.DrinkRequestDTO;
import API.Foodlivery.app.entities.dto.DrinksDTO;
import API.Foodlivery.app.service.DrinkService;
import API.Foodlivery.app.tools.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/Drinks")
public class DrinkController {
    @Autowired
    DrinkService drinkService;

    @Autowired
    Validation validation;

    Logger LOGGER = LoggerFactory.getLogger(DrinkController.class);

    @PostMapping("/create-new-drinks")
    public ResponseEntity insertNewDrinks(@RequestBody DrinkRequestDTO dto){
        LOGGER.info("Checking empty fields...");
        for(DrinksDTO x : dto.getListOfDrinks()){
            HashSet<String> errors = validation.checkRegisterDrink(x);
            if(!errors.isEmpty()) return ResponseEntity.badRequest().body(errors);
        }
        LOGGER.info("Check complete");

        try{
            drinkService.saveDrinks(dto);
            LOGGER.info("All your products have been successfully saved");
            return ResponseEntity.ok().body("All your products have been added");
        }catch (Exception ex){
            LOGGER.error(ex.getMessage());
            return ResponseEntity.internalServerError().body(ex);
        }
    }
}
