package app.application.ecobikerental.service;

import app.application.ecobikerental.entity.Bike;
import app.application.ecobikerental.entity.BikeStation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BikeService {
    Bike detailBike(Long idBike);
    boolean renderBikeInStation(Long idStation, Long idBikeType);

    List<Bike> getListBikeIntoStation(Long idStation);
    List<Bike> getListBikeHaveType(Long idType);

    List<Bike> findAll();

}
