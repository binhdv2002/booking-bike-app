package app.application.ecobikerental.service.impl;

import app.application.ecobikerental.entity.BikeStation;
import app.application.ecobikerental.repository.BikeStationRepository;
import app.application.ecobikerental.service.BikeStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BikeStationServiceImpl implements BikeStationService {

    @Autowired
    private BikeStationRepository bikeStationRepository;
    @Override
    public List<BikeStation> getListBikeStation() {
        List<BikeStation> list = bikeStationRepository.getListBikeStation();
        return list.size()>0? list : null;
    }

    @Override
    public boolean renderBikeStation() {
        BikeStation a = new BikeStation().builder().

                address(" Hai Bà Trưng, Hà Nội, Việt Nam").
                totalBikes(0).
                name("2R3W+W58, Bãi gửi xe, Bách Khoa,").
                acreage_park(2000).
                capcity(500).
                emptySlots(500).
                walking_distance(550)
                .build();
        BikeStation b = new BikeStation().builder().
                address(" P. Trần Đại Nghĩa, Bách Khoa, Hai Bà Trưng, Hà Nội, Việt Nam").
                name("2R4V+XCX Bãi gửi xe C1").
                totalBikes(0).
                acreage_park(1800).
                capcity(400).
                emptySlots(400).
                walking_distance(150)
                .build();

        BikeStation c = new BikeStation().builder().
                address("Hai Bà Trưng, Hà Nội, Việt Nam").
                name("2V52+62 Hai Bà Trưng").
                totalBikes(0).
                acreage_park(2200).
                capcity(800).
                emptySlots(800).
                walking_distance(850)
                .build();

        BikeStation d = new BikeStation().builder().
                address(" Hà Nội, Việt Nam").
                name("XRXV+9Q Hai Bà Trưng").
                totalBikes(0).
                acreage_park(1500).
                capcity(400).
                emptySlots(400).
                walking_distance(850)
                .build();
        List<BikeStation> bikeStationList = List.of(a,b,c,d);
        bikeStationList.forEach(t-> bikeStationRepository.save(t));
        return bikeStationList.size()> 0 ;
    }

    @Override
    public BikeStation detailBikeStation(Long id) {
        BikeStation bikeStation = bikeStationRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Bike station isn't exist"));
        return  bikeStation;
    }

    @Override
    public List<BikeStation> searchByName(String name) {
        return bikeStationRepository.searchByName(name);
    }

}
