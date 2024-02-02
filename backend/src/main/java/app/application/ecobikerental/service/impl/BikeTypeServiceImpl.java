package app.application.ecobikerental.service.impl;

import app.application.ecobikerental.entity.BikeType;
import app.application.ecobikerental.repository.BikeTypeRepository;
import app.application.ecobikerental.service.BikeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class BikeTypeServiceImpl implements BikeTypeService {
    @Autowired
    private BikeTypeRepository bikeTypeRepository;
    @Override
    public boolean addType(BikeType bikeType) {
        BikeType bikeTypeSave = bikeTypeRepository.save(bikeType);
        return bikeTypeSave!=null;
    }

    @Override
    public boolean renderBikeType() {
        BikeType bikeType1 = BikeType
                .builder()
                .typeName("Xe đạp đơn")
                .depositPrice(400000)
                .rentalPrice((float) (400000 * 100) /40)
                .build();
        BikeType bikeType2 = BikeType
                .builder()
                .typeName("Xe đạp đơn điện")
                .depositPrice(700000)
                .rentalPrice((float) (700000 * 100) /40)
                .build();
        BikeType bikeType3 = BikeType
                .builder()
                .typeName("Xe đạp đôi")
                .depositPrice(550000)
                .rentalPrice((float) (550000 * 100) /40)
                .build();
        List<BikeType> bikeTypeList = List.of(bikeType1,bikeType2,bikeType3);
        bikeTypeList.forEach(t-> bikeTypeRepository.save(t));
        return bikeTypeList.size()>0;
    }

    @Override
    public BikeType detailBikeType(long idBikeType) {
        BikeType bikeTypeSave = bikeTypeRepository.findById(idBikeType).orElseThrow(()-> new IllegalArgumentException("Bike type isn't exist"));
        return bikeTypeSave;
    }

    @Override
    public List<BikeType> getListBikeType() {
        List<BikeType> bikeTypeList = bikeTypeRepository.getListBikeType();

        return bikeTypeList.size()>0 ? bikeTypeList: null;
    }
}
