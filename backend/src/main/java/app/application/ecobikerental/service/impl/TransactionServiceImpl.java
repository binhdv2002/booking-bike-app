package app.application.ecobikerental.service.impl;

import app.application.ecobikerental.entity.CreditCard;
import app.application.ecobikerental.entity.Transaction;
import app.application.ecobikerental.repository.CreditCardRepository;
import app.application.ecobikerental.repository.TransactionRepository;
import app.application.ecobikerental.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public Transaction showTransaction(Long idCreditCard) {
        CreditCard creditCard = creditCardRepository.findById(idCreditCard).orElseThrow(()-> new IllegalArgumentException("Not found credit card "));
        Transaction transaction = transactionRepository.getTransactionByCreditCard(creditCard).orElseThrow(()-> new IllegalArgumentException("Not found transaction of this credit card "));
        return transaction!=null? transaction: null;
    }


    @Override
    public boolean checkTransactionNotReturn(Long idUser) {
        List<Transaction> lst = transactionRepository.getListTransactionIsReturn(idUser);
        return lst.size()>0;
    }

    @Override
    public List<Transaction> getListTransaction(Long idUser) {
        List<Transaction> lst = transactionRepository.getListTransaction(idUser);
        return lst.size()>0?lst:null;
    }
}
