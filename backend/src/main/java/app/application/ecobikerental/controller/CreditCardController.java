package app.application.ecobikerental.controller;

import app.application.ecobikerental.entity.CreditCard;
import app.application.ecobikerental.entity.Transaction;
import app.application.ecobikerental.entity.User;
import app.application.ecobikerental.service.CreditCardService;
import app.application.ecobikerental.service.TransactionService;
import app.application.ecobikerental.service.dto.request.CreditCardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/credit-card")
public class CreditCardController {
    @Autowired
    private CreditCardService creditCardService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/createCard")
    public ResponseEntity<?> createCard(@RequestBody CreditCardRequest creditCardRequest){
        try {
            CreditCard creditCard = creditCardService.createCreditCard(creditCardRequest);
            return creditCard !=null ?
                    ResponseEntity.status(200).body(creditCard):
                    ResponseEntity.status(400).body("Create credit card is failure!");
        }catch (Exception e ) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/getUser")
    public ResponseEntity<?> getUsername(@RequestParam("idCard") Long idCard){
        try {
            User user = creditCardService.getUser(idCard);
            return ResponseEntity.status(200).body(user);
        }catch (Exception e ) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/getTransaction/{idCard}")
    public ResponseEntity<?> getTransaction(@PathVariable("idCard") long idCard){
        try {
            Transaction transaction = transactionService.showTransaction(idCard);
            return transaction !=null ?
                    ResponseEntity.status(200).body(transaction):
                    ResponseEntity.status(400).body("Get transaction is false ");
        }catch (Exception e ) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/getCreditCard/{idCard}")
    public ResponseEntity<?> getCreditCard(@PathVariable("idCard") long idCard){
        try {
            CreditCard creditCard = creditCardService.getCreditCard(idCard);
            return creditCard !=null ?
                    ResponseEntity.status(200).body(creditCard):
                    ResponseEntity.status(400).body("Get transaction is false ");
        }catch (Exception e ) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/resetCreditCard/{idCard}")
    public ResponseEntity<?> resetCreditCard(@PathVariable("idCard") long idCard){
        try {
            boolean isReset = creditCardService.resetCreditCard(idCard);
            return isReset?
                    ResponseEntity.status(200).body("Reset credit card is successfully!"):
                    ResponseEntity.status(400).body("Reset credit card is failure!");
        }catch (Exception e ) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


}
