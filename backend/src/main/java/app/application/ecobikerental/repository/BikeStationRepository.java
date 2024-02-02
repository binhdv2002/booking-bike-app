package app.application.ecobikerental.repository;

import app.application.ecobikerental.entity.BikeStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BikeStationRepository extends CrudRepository<BikeStation, Long> {
    @Transactional
    @Query("select bs from BikeStation bs")
    List<BikeStation> getListBikeStation();
    @Query("select bs from BikeStation bs where bs.name like concat('%', ?1, '%') ")
    List<BikeStation> searchByName(String name);
}
