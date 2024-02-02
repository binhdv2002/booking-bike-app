package app.application.ecobikerental.controller;

import app.application.ecobikerental.entity.Bike;
import app.application.ecobikerental.service.BikeService;
import app.application.ecobikerental.service.dto.request.BikeRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bike")
public class BikeController {
    @Autowired
    private BikeService bikeService;

    @GetMapping("/detailBike/{id}")
    public ResponseEntity<?> detailBike(@PathVariable("id") Long id ){
        try {
            Bike bike = bikeService.detailBike(id);
            return ResponseEntity.status(200).body(bike);
        }catch (Exception e ){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/renderBike")
    public ResponseEntity<?> renderBike(@RequestBody BikeRequest request ){
        try {
            boolean render= bikeService.renderBikeInStation(request.getIdStation(), request.getIdType());
            return ResponseEntity.status(200).body("Render bike is successful");
        }catch (Exception e ){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/getListBikeIntoStation/{idStation}")
    public ResponseEntity<?> getListBikeIntoStation(@PathVariable("idStation") Long idStation ){
        try {
            List<Bike> listBikeIntoStation = bikeService.getListBikeIntoStation(idStation);
            return listBikeIntoStation==null ?
                    ResponseEntity.status(400).body("Not found bike into this station"):
                    ResponseEntity.status(200).body(listBikeIntoStation);
        }catch (Exception e ){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/getListBikeHaveType/{idType}")
    public ResponseEntity<?> getListBikeHaveType(@PathVariable("idType") Long idType ){
        try {
            List<Bike> listBikeHaveType = bikeService.getListBikeHaveType(idType);
            return listBikeHaveType==null ?
                    ResponseEntity.status(400).body("Not found bike into this station"):
                    ResponseEntity.status(200).body(listBikeHaveType);
        }catch (Exception e ){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        try {
            List<Bike> listBikeHaveType = bikeService.findAll();
            return listBikeHaveType==null ?
                    ResponseEntity.status(400).body("Not found bike into this station"):
                    ResponseEntity.status(200).body(listBikeHaveType);
        }catch (Exception e ){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
