package app.application.ecobikerental.repository;

import app.application.ecobikerental.entity.BikeType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BikeTypeRepository extends CrudRepository<BikeType, Long > {
    @Transactional
    @Query("select bt from BikeType bt")
    List<BikeType> getListBikeType();
}
