package app.application.ecobikerental.service;

import app.application.ecobikerental.entity.BikeRental;

public interface ReturnService {
    boolean totalCostNormal(Long idRental) ;
    boolean totalCostNormalNew(Long idRental) ;
}
