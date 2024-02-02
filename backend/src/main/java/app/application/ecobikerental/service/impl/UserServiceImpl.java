package app.application.ecobikerental.service.impl;

import app.application.ecobikerental.entity.CreditCard;
import app.application.ecobikerental.entity.User;
import app.application.ecobikerental.repository.UserRepository;
import app.application.ecobikerental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public CreditCard getCardOfUser(Long idUser) {
        User user = userRepository.findById(idUser).orElseThrow(()-> new IllegalArgumentException("User not exist "));
        CreditCard creditCard = userRepository.getCreditFromUser(user.getId()).orElseThrow(()-> new IllegalArgumentException("User haven't a credit card"));
        return creditCard;
    }
}
