package app.application.ecobikerental.service;

import app.application.ecobikerental.entity.BikeStation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BikeStationService {
    List<BikeStation> getListBikeStation();

    boolean renderBikeStation();
    BikeStation detailBikeStation(Long id);

    List<BikeStation> searchByName(String name);
}
