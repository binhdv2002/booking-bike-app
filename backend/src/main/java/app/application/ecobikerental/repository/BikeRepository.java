package app.application.ecobikerental.repository;

import app.application.ecobikerental.entity.Bike;
import app.application.ecobikerental.entity.BikeStation;
import app.application.ecobikerental.entity.BikeType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BikeRepository extends CrudRepository<Bike , Long > {
    @Transactional
    @Query("select bike from Bike bike where bike.bikeStation = :station and bike.status = true ")
    List<Bike>getListBikeIntoStation(@Param("station") BikeStation station);

    @Transactional
    @Query("select bike from Bike bike where bike.bikeType = :type")
    List<Bike>getListBikeHaveType(@Param("type") BikeType type);

    @Transactional
    @Query("select bike from Bike bike where bike.barcode = :barcode")
    Optional<Bike> getBikeByBarcode(@Param("barcode") String barcode);
}
