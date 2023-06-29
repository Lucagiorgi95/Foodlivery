package API.Foodlivery.app.comon.controllers;

import API.Foodlivery.app.comon.dto.ReviewDTO;
import API.Foodlivery.app.comon.dto.ReviewRTO;
import API.Foodlivery.app.comon.service.ReviewService;
import API.Foodlivery.app.comon.tools.CheckEmptyField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger LOGGER = LoggerFactory.getLogger(ReviewController.class);

    @PostMapping("/insert-new-review")
    public ResponseEntity createNewReview(@RequestBody ReviewDTO reviewDTO){
        LOGGER.info("Checking empty fields...");
        HashSet<String> errors = checkEmptyField.checkRegisterReview(reviewDTO);
        if (!errors.isEmpty()) return ResponseEntity.badRequest().body(errors);
        LOGGER.info("Check complete");

        try{
            ReviewRTO review = reviewService.saveNewReview(reviewDTO);
            LOGGER.info("Your review has been successfully saved");
            return ResponseEntity.ok(review);
        }catch (Exception ex){
            LOGGER.error(ex.getMessage());
            return ResponseEntity.internalServerError().body(ex);
        }
    }
}
