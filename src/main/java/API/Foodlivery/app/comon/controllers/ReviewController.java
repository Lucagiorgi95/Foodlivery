package API.Foodlivery.app.comon.controllers;

import API.Foodlivery.app.comon.dto.ReviewDTO;
import API.Foodlivery.app.comon.dto.ReviewRTO;
import API.Foodlivery.app.comon.service.ReviewService;
import API.Foodlivery.app.comon.tools.CheckEmptyField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/Review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    CheckEmptyField checkEmptyField;

    @PostMapping("/insert-new-review")
    public ResponseEntity createNewReview(@RequestBody ReviewDTO dto){
        HashSet<String> errors = checkEmptyField.checkRegisterReview(dto);
        if (!errors.isEmpty()) return ResponseEntity.badRequest().body(errors);

        try{
            ReviewRTO review = reviewService.saveNewReview(dto);
            return ResponseEntity.ok(review);
        }catch (Exception ex){
            System.out.println(ex);
            return ResponseEntity.internalServerError().body(errors);
        }
    }
}
