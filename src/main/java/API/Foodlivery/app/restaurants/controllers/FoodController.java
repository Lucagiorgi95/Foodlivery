package API.Foodlivery.app.restaurants.controllers;

import API.Foodlivery.app.comon.tools.CheckEmptyField;
import API.Foodlivery.app.restaurants.DtoRto.FoodDTO;
import API.Foodlivery.app.restaurants.DtoRto.FoodRequestDTO;
import API.Foodlivery.app.restaurants.service.FoodService;
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

    @PostMapping("/create-new-food")
    public ResponseEntity insertNewFood(@RequestBody FoodRequestDTO dto){
        for(FoodDTO x : dto.getListOfFood()){
            HashSet<String> errors = checkEmptyField.checkRegisterFood(x);
            if(!errors.isEmpty()) return ResponseEntity.badRequest().body(errors);
        }

        try{
            foodService.saveFood(dto);
            return ResponseEntity.ok().body("All your products have been added");
        }catch (Exception ex){
            ex.getStackTrace();
            return ResponseEntity.internalServerError().body(ex);
        }

    }
}
