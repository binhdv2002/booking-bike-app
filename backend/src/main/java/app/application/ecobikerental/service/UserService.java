package app.application.ecobikerental.service;

import app.application.ecobikerental.entity.CreditCard;
import app.application.ecobikerental.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    CreditCard getCardOfUser(Long idUser);
}
