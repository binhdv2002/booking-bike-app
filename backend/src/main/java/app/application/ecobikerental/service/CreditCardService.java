package app.application.ecobikerental.service;

import app.application.ecobikerental.entity.CreditCard;
import app.application.ecobikerental.entity.User;
import app.application.ecobikerental.service.dto.request.CreditCardRequest;
import org.springframework.stereotype.Service;

@Service
public interface CreditCardService {
    CreditCard createCreditCard(CreditCardRequest creditCardRequest);
    boolean resetCreditCard(Long idCard );
    CreditCard getCreditCard(Long idCard );
    User getUser (Long idCard);
}
