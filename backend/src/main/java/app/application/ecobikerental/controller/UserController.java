package app.application.ecobikerental.controller;

import app.application.ecobikerental.entity.CreditCard;
import app.application.ecobikerental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService ;

    @GetMapping("/getCreditCardFromUser/{idUser}")
    public ResponseEntity<?> getCreditCardFromUser(@PathVariable("idUser") long idUser){
        try {
            CreditCard creditCard = userService.getCardOfUser(idUser);
            return ResponseEntity.status(200).body(creditCard);
        }
        catch (Exception e )
        {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
