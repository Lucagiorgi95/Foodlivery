package API.Foodlivery.app.controllers;

import API.Foodlivery.app.entities.dto.FoodDTO;
import API.Foodlivery.app.entities.dto.FoodRequestDTO;
import API.Foodlivery.app.service.FoodService;
import API.Foodlivery.app.tools.Validation;
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
    Validation validation;

    Logger LOGGER = LoggerFactory.getLogger(FoodController.class);

    @PostMapping("/create-new-food")
    public ResponseEntity insertNewFood(@RequestBody FoodRequestDTO dto){
        try{
            LOGGER.info("Checking empty fields...");
            for(FoodDTO x : dto.getListOfFood()){
                HashSet<String> errors = validation.checkRegisterFood(x);
                if(!errors.isEmpty()) return ResponseEntity.badRequest().body(errors);
            }
            LOGGER.info("Check complete");
            foodService.saveFood(dto);
            LOGGER.info("All your products have been successfully saved");
            return ResponseEntity.ok().body("All your products have been added");
        }catch (Exception ex){
            LOGGER.error(ex.getMessage());
            return ResponseEntity.internalServerError().body("Impossible save the foods");
        }
    }

    @GetMapping("/get-food")
    public ResponseEntity getFood(@RequestParam long id){
        try {
            LOGGER.info("Find food by id....");
            FoodDTO dto = foodService.findById(id);
            HashSet<String> emptyField = validation.checkRtoFood(dto);
            if(emptyField.isEmpty()){
                LOGGER.info("Find complete");
                return ResponseEntity.ok(dto);
            }else {
                LOGGER.error("Find error");
                return ResponseEntity.badRequest().body(emptyField);
            }
        }catch (Exception ex){
            LOGGER.error(ex.getMessage());
            return ResponseEntity.internalServerError().body(String.format("Impossible get food whit id: %s", id));
        }
    }
}
