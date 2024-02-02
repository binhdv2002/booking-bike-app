package app.application.ecobikerental.repository;

import app.application.ecobikerental.entity.CreditCard;
import app.application.ecobikerental.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CreditCardRepository extends CrudRepository<CreditCard , Long > {

    @Transactional
    @Query("select user from User user where user.creditCard.id = :idCard ")
    Optional<User> findByIdCard(@Param("idCard") Long idCard );
}
