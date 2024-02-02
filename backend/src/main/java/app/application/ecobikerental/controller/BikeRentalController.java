package app.application.ecobikerental.controller;

import app.application.ecobikerental.entity.BikeRental;
import app.application.ecobikerental.service.BikeRentalService;
import app.application.ecobikerental.service.BikeService;
import app.application.ecobikerental.service.dto.request.RentalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bike-rental")
public class BikeRentalController {
    @Autowired
    private BikeRentalService bikeRentalService;

    @PostMapping("/rentalBike")
    public ResponseEntity<?> rentalBike(@RequestBody RentalRequest rentalRequest){
        try {
            BikeRental bikeRental = bikeRentalService.rentalBike(rentalRequest);
            return bikeRental!=null ?
                    ResponseEntity.status(200).body(bikeRental) :
                    ResponseEntity.status(400).body("Rental bike is failure");
        }catch (Exception e ){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/rentalBikeNew")
    public ResponseEntity<?> rentalBikeNew(@RequestBody RentalRequest rentalRequest){
        try {
            BikeRental bikeRental = bikeRentalService.rentalBikeNew(rentalRequest);
            return bikeRental!=null ?
                    ResponseEntity.status(200).body(bikeRental) :
                    ResponseEntity.status(400).body("Rental bike is failure");
        }catch (Exception e ){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/getBarcodeBike/{idBike}")
    public ResponseEntity<?> getBarcodeBike(@PathVariable("idBike") Long idBike ){
        try {
            byte[] barcode = bikeRentalService.generateBarcode(idBike);
            return ResponseEntity.status(200).contentType(MediaType.IMAGE_PNG).body(barcode);
        }catch (Exception e ){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/getBarcodeBikeValue/{idBike}")
    public ResponseEntity<?> getBarcodeBikeValue(@PathVariable("idBike") Long idBike ){
        try {
            String barcodeValue = bikeRentalService.generateBarcodeValue(idBike);
            return ResponseEntity.status(200).body(barcodeValue);
        }catch (Exception e ){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        try {
            BikeRental bikRental = bikeRentalService.findById(id);
            if(bikRental != null) {
                return ResponseEntity.status(200).body(bikRental);
            }
            return ResponseEntity.status(400).body("Not Found!");
        }catch (Exception e ){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
