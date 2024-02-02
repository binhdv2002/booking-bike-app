package app.application.ecobikerental.repository;

import app.application.ecobikerental.entity.BikeRental;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BikeRentalRepository extends CrudRepository<BikeRental, Long> {
    @Transactional
    @Query("select rental from BikeRental rental where rental.bikeRentalStatus = 'PENDING' and  rental.user.id = :idUser ")
    Optional<BikeRental> findBikeRentalByUser(@Param("idUser") Long idUser);
}
