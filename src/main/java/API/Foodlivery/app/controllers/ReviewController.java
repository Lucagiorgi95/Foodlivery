package API.Foodlivery.app.controllers;

import API.Foodlivery.app.entities.dto.ReviewDTO;
import API.Foodlivery.app.entities.rto.ReviewRTO;
import API.Foodlivery.app.service.ReviewService;
import API.Foodlivery.app.tools.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/Review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;
    @Autowired
    Validation validation;
    Logger LOGGER = LoggerFactory.getLogger(ReviewController.class);

    /**
     * Metodo per creare una nuova recenzione
     *
     * @param reviewDTO                  Recenzione da creare
     * @return                           Ritorna una ResponseEntity in base all'esito
     */
    @PostMapping("/insert-new-review")
    public ResponseEntity createNewReview(@RequestBody ReviewDTO reviewDTO){
        try{
            LOGGER.info("Checking empty fields...");
            HashSet<String> errors = validation.checkRegisterReview(reviewDTO);
            if (!errors.isEmpty()) return ResponseEntity.badRequest().body(errors);
            LOGGER.info("Check complete");

            ReviewRTO review = reviewService.saveNewReview(reviewDTO);
            LOGGER.info("Your review has been successfully saved");
            return ResponseEntity.ok(review);
        }catch (Exception ex){
            LOGGER.error(ex.getMessage());
            return ResponseEntity.internalServerError().body(ex);
        }
    }

    /**
     * Metodo per ottenere una determinate recenzione
     *
     * @param id                        L'id della recenzione
     * @return                          Ritorna una ResponseEntity in base all'esito
     */
    @GetMapping("/get-review")
    public ResponseEntity viewReview(@RequestParam long id){
        try{
            LOGGER.info("Find review...");
            ReviewRTO rto = reviewService.findReview(id);
            HashSet<String> result = validation.checkRtoReview(rto);
            if(result.isEmpty()){
                LOGGER.info("Review whit ID: " + id + "is find");
                return ResponseEntity.ok(rto);
            }else {
                LOGGER.error("Review not found");
                return ResponseEntity.badRequest().body("Review with ID: " + id + "is not exist");
            }
        }catch (Exception ex){
            LOGGER.error(ex.getMessage());
            return ResponseEntity.internalServerError().body("Unable to find review");
        }
    }

    /**
     * Metodo per ottenere tutte le reviews di un ristorante
     *
     * @param restaurantId           Id del ristorante
     * @return                       Ritorna una ResponseEntity in base all'esito
     */
    @GetMapping("/get-all-review-of-restaurant")
    public ResponseEntity viewAllReviewsOfRestaurant(@RequestParam long restaurantId){
        try{
            LOGGER.info("Find reviews...");
            List<ReviewRTO> list = reviewService.findAllReviewOfRestaurant(restaurantId);
            if(!list.isEmpty()){
                LOGGER.info("Find restaurant reviews");
                return ResponseEntity.ok(list);
            }else{
             LOGGER.error("The restaurant has no reviews");
             return ResponseEntity.badRequest().body("The restaurant has no reviews");
            }
        }catch (Exception ex){
            LOGGER.error(ex.getMessage());
            return ResponseEntity.internalServerError().body("Impossible to find restaurant reviews");
        }
    }

    /**
     * Metodo per ottenere tutte le reviews di un user
     *
     * @param userId                Id dell'utente
     * @return                      Ritorna una ResponseEntity in base all'esito
     */
    @GetMapping("/get-all-review-of-user")
    public ResponseEntity viewAllReviewsOfUser(@RequestParam long userId){
        try{
            LOGGER.info("Find reviews...");
            List<ReviewRTO> list = reviewService.findAllReviewOfUser(userId);
            if(!list.isEmpty()){
                LOGGER.info("Find user reviews");
                return ResponseEntity.ok(list);
            }else{
                LOGGER.error("The user has no reviews");
                return ResponseEntity.badRequest().body("The user has no reviews");
            }
        }catch (Exception ex){
            LOGGER.error(ex.getMessage());
            return ResponseEntity.internalServerError().body("Impossible to find user reviews");
        }
    }
}
