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
public interface UserRepository extends CrudRepository<User, Long> {
    @Transactional
    @Query("select credit from CreditCard credit where credit.id = :idUser ")
    Optional<CreditCard> getCreditFromUser(@Param("idUser") Long idUser);

}
