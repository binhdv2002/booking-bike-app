package app.application.ecobikerental.service;

import app.application.ecobikerental.entity.BikeType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BikeTypeService {
    boolean addType(BikeType bikeType);
    boolean renderBikeType();

    BikeType detailBikeType(long idBikeType);

    List<BikeType> getListBikeType();
}
