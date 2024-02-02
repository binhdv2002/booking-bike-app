package app.application.ecobikerental.service.impl;

import app.application.ecobikerental.entity.*;
import app.application.ecobikerental.repository.*;
import app.application.ecobikerental.service.CreditCardService;
import app.application.ecobikerental.service.dto.enums.BikeRentalStatus;
import app.application.ecobikerental.service.dto.request.CreditCardRequest;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class CreditCardImpl implements CreditCardService {
    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BikeRentalRepository bikeRentalRepository;

    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public CreditCard createCreditCard(CreditCardRequest creditCardRequest) {
        CreditCard creditCard = CreditCard.builder().
                cardNumber(creditCardRequest.getCardNumber()).
                cvv(creditCardRequest.getCvv()).
                balance(1000000).
                expiredDate(LocalDate.now().plusDays(1)).
                build();
        CreditCard creditCardSave =  creditCardRepository.save(creditCard);
        Random r = new Random();
        User user = User.builder().
                email("account"+System.currentTimeMillis()+"@gmail.com").
                password(BCrypt.hashpw(String.valueOf(r.nextInt(0,1000000)),BCrypt.gensalt())).creditCard(creditCard).build();
        userRepository.save(user);

        Bike bike = bikeRepository.findById(creditCardRequest.getIdBike()).orElseThrow(()-> new IllegalArgumentException("Bike not found in system"));


        return creditCardSave!=null?creditCardSave:null;
    }

    @Override
    public boolean resetCreditCard(Long idCard) {
        CreditCard creditCard = creditCardRepository.findById(idCard).orElseThrow(()-> new IllegalArgumentException("Credit card is not exist"));
        creditCard.setBalance(1000000);
        return creditCard!=null;
    }

    @Override
    public CreditCard getCreditCard(Long idCard) {
        CreditCard creditCard = creditCardRepository.findById(idCard).orElseThrow(()-> new IllegalArgumentException("Credit card is not exist"));
        return creditCard!=null?creditCard:null;
    }

    @Override
    public User getUser(Long idCard) {
        User user = creditCardRepository.findByIdCard(idCard).orElseThrow(()-> new IllegalArgumentException("User not found into database "));
        return user;
    }
}
