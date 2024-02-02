package app.application.ecobikerental.service.impl;

import app.application.ecobikerental.entity.Bike;
import app.application.ecobikerental.entity.BikeStation;
import app.application.ecobikerental.entity.BikeType;
import app.application.ecobikerental.repository.BikeRepository;
import app.application.ecobikerental.repository.BikeStationRepository;
import app.application.ecobikerental.repository.BikeTypeRepository;
import app.application.ecobikerental.service.BikeService;
import app.application.ecobikerental.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BikeServiceImpl implements BikeService {
    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private BikeStationRepository bikeStationRepository;

    @Autowired
    private BikeTypeRepository bikeTypeRepository;
    @Override
    public Bike detailBike(Long id) {
        Bike bike = bikeRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Bike isn't exist"));
        return bike;
    }

    @Override
    public boolean renderBikeInStation(Long idStation, Long idBikeType) {
        BikeStation station = bikeStationRepository.
                findById(idStation).
                orElseThrow(()->new IllegalArgumentException("Station isn't exist"));
        BikeType bikeType = bikeTypeRepository.
                findById(idBikeType).
                orElseThrow(()->new IllegalArgumentException("Bike type isn't exist"));

        Bike bike1 = Bike.builder()
                .bikeStation(station)
                .status(true)
                .bikeType(bikeType)
                .batteryLevel(100)
                .barcode(Utility.randomBarcode())
                .licensePlate(Utility.randomLicensePlate())
                .build();
        Bike bike2 = Bike.builder()
                .bikeStation(station)
                .status(true)
                .bikeType(bikeType)
                .barcode(Utility.randomBarcode())
                .batteryLevel(100)
                .licensePlate(Utility.randomLicensePlate())
                .build();
        Bike bike3 = Bike.builder()
                .bikeStation(station)
                .status(true)
                .bikeType(bikeType)
                .batteryLevel(100)
                .barcode(Utility.randomBarcode())
                .licensePlate(Utility.randomLicensePlate())
                .build();
        Bike bike4 = Bike.builder()
                .bikeStation(station)
                .status(true)
                .bikeType(bikeType)
                .barcode(Utility.randomBarcode())
                .batteryLevel(100)
                .licensePlate(Utility.randomLicensePlate())
                .build();
        List<Bike> list = List.of(bike1, bike2 , bike3 ,bike4);
        station.setTotalBikes(station.getTotalBikes()+4);
        station.setEmptySlots(station.getEmptySlots()-4);
        bikeRepository.saveAll(list);
        return false;
    }

    @Override
    public List<Bike> getListBikeIntoStation(Long idStation) {
        BikeStation station = bikeStationRepository.
                findById(idStation).
                orElseThrow(()->new IllegalArgumentException("Station isn't exist"));
        List<Bike> bikes = bikeRepository.getListBikeIntoStation(station);
        return bikes.size() > 0 ? bikes : null;
    }

    @Override
    public List<Bike> getListBikeHaveType(Long idType) {
        BikeType bikeType = bikeTypeRepository.
                findById(idType).
                orElseThrow(()->new IllegalArgumentException("Bike type isn't exist"));
        List<Bike> bikes = bikeRepository.getListBikeHaveType(bikeType);
        return bikes.size() > 0 ? bikes : null;
    }

    @Override
    public List<Bike> findAll() {
        Iterable<Bike> bikesRepo = bikeRepository.findAll();
        List<Bike> bikes = new ArrayList<>();
        bikesRepo.forEach(bikes::add);
        return bikes;
    }

}
