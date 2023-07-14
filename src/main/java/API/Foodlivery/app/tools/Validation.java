package API.Foodlivery.app.tools;

import API.Foodlivery.app.entities.dto.*;
import API.Foodlivery.app.entities.rto.ReviewRTO;
import API.Foodlivery.app.entities.rto.UserRTO;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.regex.Pattern;

@Service
public class Validation {

    public HashSet<String> checkRegisterUser(UserDTO dto){
        HashSet<String> errors = new HashSet<>();
        if(dto.getName() == null) errors.add("Name is not insert");
        if(dto.getSurname() == null) errors.add("Surname is not insert");
        if(dto.getDateOfBirth() == null) errors.add("Date of birt is not insert");
        if(!patternMatches(dto.getEmail())) errors.add("Email is not insert");
        if(dto.getPassword() == null) errors.add("Password is not insert");
        if(dto.getAddress() == null) errors.add("Address is not insert");
        return errors;
    }

    public HashSet<String> checkRegisterRestaurant(RestaurantDTO dto){
        HashSet<String> errors = new HashSet<>();
        if(dto.getName() == null) errors.add("Name is not insert");
        if (dto.getType() == null) errors.add("Type is not insert");
        if (dto.getAddress() == null) errors.add("Address is not insert");
        return errors;
    }

    public HashSet<String> checkRegisterFood (FoodDTO dto){
        HashSet<String> errors = new HashSet<>();
        if (dto.getName() == null) errors.add("Name is not insert");
        if (dto.getPrize() == 0) errors.add("Prize is not insert");
        if (dto.getDescription() == null) errors.add("Description is not insert");
        return errors;
    }

    public HashSet<String> checkRegisterDrink (DrinksDTO dto){
        HashSet<String> errors = new HashSet<>();
        if (dto.getName() == null) errors.add("Name is not insert");
        if (dto.getPrize() == 0) errors.add("Prize is not insert");
        if (dto.getDescription() == null) errors.add("Description is not insert");
        return errors;
    }

    public HashSet<String> checkRegisterReview (ReviewDTO dto){
        HashSet<String> errors = new HashSet<>();
        if (dto.getUserId() == 0) errors.add("UserID is not insert");
        if (dto.getRestaurantId() == 0) errors.add("RestaurantID is not insert");
        if (dto.getStar() == 0) errors.add("Star rating is not insert");
        if (dto.getDescription() == null) errors.add("Description is not insert");
        return errors;
    }

    /**
     * Metodo per la validazione dell'email al momento della registrazione
     * Le seguenti restrizioni sono imposte nella parte locale dell'indirizzo e-mail utilizzando questa regex:
     * - Consente valori numerici da 0 a 9.
     * - Sono consentite sia lettere maiuscole che minuscole dalla a alla z.
     * - Sono consentiti il carattere di sottolineatura "_", il trattino "-" e il punto "."
     * - Il punto non è consentito all'inizio e alla fine della parte locale.
     * - Non sono consentiti punti consecutivi.
     * - Per la parte locale, sono consentiti un massimo di 64 caratteri.
     * Le restrizioni per la parte di dominio in questa espressione regolare includono:
     * - Freestar
     * - Consente valori numerici da 0 a 9.
     * - Sono consentite lettere maiuscole e minuscole dalla a alla z.
     * - Il trattino "-" e il punto "." non sono consentiti all'inizio e alla fine della parte di dominio.
     * - Nessun punto consecutivo.
     *
     * @param emailAddress               Email da validare
     * @return                           True o False
     */
    public static boolean patternMatches(String emailAddress) {
        return Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
                .matcher(emailAddress)
                .matches();
    }

    //Check Response
    public HashSet<String> checkRtoFood(FoodDTO rto){
        HashSet<String> emptyField = new HashSet<>();
        if(rto.getName() == null && rto.getPrize() == 0 && rto.getDescription() == null) emptyField.add("Error");
        return emptyField;
    }
    public HashSet<String> checkRtoUser(UserRTO rto) {
        HashSet<String> emptyField = new HashSet<>();
        if(rto.getName() == null && rto.getSurname() == null && rto.getEmail() == null &&
                rto.getAddress() == null && rto.getDateOfBirth() == null && rto.getTelephoneNumber() == null) {
            emptyField.add("Error");
        }
        return emptyField;
    }


    public HashSet<String> checkRtoReview(ReviewRTO rto) {
        HashSet<String> emptyField = new HashSet<>();
        if(rto.getDescription() == null && rto.getStar() == 0){
            emptyField.add("Error");
        }
        return emptyField;
    }


}
