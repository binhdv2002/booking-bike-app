package app.application.ecobikerental.service;

import app.application.ecobikerental.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    Transaction showTransaction(Long idCreditCard);
    boolean checkTransactionNotReturn (Long idUser);

    List<Transaction> getListTransaction(Long idUser);
}
