package app.application.ecobikerental.repository;

import app.application.ecobikerental.entity.CreditCard;
import app.application.ecobikerental.entity.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    @Transactional
    @Query("select ts from Transaction ts where ts.creditCard = :creditCard")
    Optional<Transaction> getTransactionByCreditCard(@Param("creditCard") CreditCard creditCard);

    @Transactional
    @Query("select ts from Transaction ts where ts.isReturn = false and ts.user.id = :idUser")
    List<Transaction> getListTransactionIsReturn(@Param("idUser") Long idUser);

    @Transactional
    @Query("select ts from Transaction ts where ts.user.id = :idUser")
    List<Transaction> getListTransaction(@Param("idUser") Long idUser);
}
