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
    public ResponseEntity createNewReview(@RequestBody ReviewDTO reviewDTO){
        HashSet<String> errors = checkEmptyField.checkRegisterReview(reviewDTO);
        if (!errors.isEmpty()) return ResponseEntity.badRequest().body(errors);

        try{
            ReviewRTO review = reviewService.saveNewReview(reviewDTO);
            return ResponseEntity.ok(review);
        }catch (Exception ex){
            ex.getStackTrace();
            return ResponseEntity.internalServerError().body(ex);
        }
    }
}
