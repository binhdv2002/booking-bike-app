package app.application.ecobikerental.service;

import app.application.ecobikerental.entity.BikeRental;
import app.application.ecobikerental.service.dto.request.RentalRequest;
import com.google.zxing.WriterException;

import java.io.IOException;
import java.util.Map;

public interface BikeRentalService {
    BikeRental rentalBike(RentalRequest rentalRequest );
    BikeRental rentalBikeNew(RentalRequest rentalRequest );
    byte[] generateBarcode(Long idBike) throws IOException, WriterException;
    String generateBarcodeValue(Long idBike) throws IOException, WriterException;

    BikeRental getRentalBikeByUser(Long idUser);

    BikeRental findById(int id);
}
